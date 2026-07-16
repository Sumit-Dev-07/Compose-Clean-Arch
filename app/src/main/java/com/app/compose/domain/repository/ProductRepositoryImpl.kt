package com.app.compose.domain.repository

import com.app.compose.data.datasource.ProductDataSource
import com.app.compose.data.remote.model.product.ProductResponse
import com.app.compose.util.ApiState
import com.app.compose.util.DispatchersProvider
import com.app.compose.util.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productDataSource: ProductDataSource,
    private val dispatchersProvider: DispatchersProvider
) : ProductRepository {
    override suspend fun getProducts(limit: Int, skip: Int): Flow<ApiState<ProductResponse>> {
        return flow {
            emit(ApiState.Loading)
            val result = safeApiCall { productDataSource.getProducts(limit, skip) }
            emit(result)
        }.flowOn(dispatchersProvider.io())
    }

    override suspend fun getProductsByCategory(category: String): Flow<ApiState<ProductResponse>> {
        return flow {
            emit(ApiState.Loading)
            val result = safeApiCall { productDataSource.getProductsByCategory(category) }
            emit(result)
        }.flowOn(dispatchersProvider.io())
    }
}
