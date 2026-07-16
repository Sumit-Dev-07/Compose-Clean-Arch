package com.app.compose.domain.usecase

import com.app.compose.data.remote.model.product.ProductResponse
import com.app.compose.domain.repository.ProductRepository
import com.app.compose.util.ApiState
import com.app.compose.util.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductUseCase @Inject constructor(private val productRepository: ProductRepository) {
    suspend fun getProducts(limit: Int, skip: Int): Flow<UiState<ProductResponse>> {
        return productRepository.getProducts(limit, skip).map { result ->
            when (result) {
                is ApiState.Loading -> UiState.Loading
                is ApiState.Success -> UiState.Success(result.data)
                is ApiState.Error -> UiState.Error(result.message)
            }
        }
    }

    suspend fun getProductsByCategory(category: String): Flow<UiState<ProductResponse>> {
        return productRepository.getProductsByCategory(category).map { result ->
            when (result) {
                is ApiState.Loading -> UiState.Loading
                is ApiState.Success -> UiState.Success(result.data)
                is ApiState.Error -> UiState.Error(result.message)
            }
        }
    }
}
