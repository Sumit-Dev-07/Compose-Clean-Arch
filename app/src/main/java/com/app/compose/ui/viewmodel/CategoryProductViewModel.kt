package com.app.compose.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class CategoryProductViewModel @Inject constructor(
    private val productUseCase: ProductUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val category: String = checkNotNull(savedStateHandle["category"])

    private val _productState = MutableStateFlow<UiState<ProductResponse>>(UiState.Idle)
    val productState: StateFlow<UiState<ProductResponse>> = _productState.asStateFlow()

    init {
        getProductsByCategory()
    }

    fun getProductsByCategory() {
        viewModelScope.launch {
            productUseCase.getProductsByCategory(category).collect {
                _productState.value = it
            }
        }
    }
}
