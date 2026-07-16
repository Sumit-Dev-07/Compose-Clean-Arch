package com.app.compose.data.datasource

import com.app.compose.data.remote.ApiService
import com.app.compose.data.remote.model.product.ProductResponse
import retrofit2.Response
import javax.inject.Inject

class ProductDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : ProductDataSource {
    override suspend fun getProducts(limit: Int, skip: Int): Response<ProductResponse> {
        return apiService.getProducts(limit, skip)
    }
}
