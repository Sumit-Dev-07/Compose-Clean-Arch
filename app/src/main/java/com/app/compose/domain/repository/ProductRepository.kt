package com.app.compose.domain.repository

import com.app.compose.data.remote.model.product.ProductResponse
import com.app.compose.util.ApiState
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProducts(limit: Int, skip: Int): Flow<ApiState<ProductResponse>>
}
