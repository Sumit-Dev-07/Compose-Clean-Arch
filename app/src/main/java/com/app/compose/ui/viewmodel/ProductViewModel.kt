package com.app.compose.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.compose.data.remote.model.product.Product
import com.app.compose.data.remote.model.product.ProductResponse
import com.app.compose.domain.usecase.ProductUseCase
import com.app.compose.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productUseCase: ProductUseCase
) : ViewModel() {

    private val _productState = MutableStateFlow<UiState<ProductResponse>>(UiState.Idle)
    val productState: StateFlow<UiState<ProductResponse>> = _productState.asStateFlow()

    private var currentSkip = 0
    private val limit = 10
    private var isLastPage = false
    private var isFetching = false
    private val allProducts = mutableListOf<Product>()

    init {
        getProducts()
    }

    fun getProducts(isRefresh: Boolean = false) {
        if (isFetching) return

        if (isRefresh) {
            currentSkip = 0
            isLastPage = false
            allProducts.clear()
        }

        if (isLastPage && !isRefresh) return

        viewModelScope.launch {
            isFetching = true
            productUseCase.getProducts(limit, currentSkip).collect { state ->
                when (state) {
                    is UiState.Success -> {
                        val response = state.data
                        allProducts.addAll(response.products)
                        currentSkip += response.products.size
                        isLastPage = allProducts.size >= response.total || response.products.isEmpty()

                        _productState.value = UiState.Success(
                            response.copy(products = allProducts.toList())
                        )
                        isFetching = false
                    }
                    is UiState.Error -> {
                        _productState.value = state
                        isFetching = false
                    }
                    is UiState.Loading -> {
                        if (allProducts.isEmpty()) {
                            _productState.value = state
                        }
                    }
                    else -> _productState.value = state
                }
            }
        }
    }
}
