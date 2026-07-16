package com.app.compose.data.datasource

import com.app.compose.data.remote.model.product.ProductResponse
import retrofit2.Response

interface ProductDataSource {
    suspend fun getProducts(): Response<ProductResponse>
}
