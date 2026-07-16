package com.app.compose.domain.repository

import com.app.compose.data.datasource.CategoryDataSource
import com.app.compose.data.remote.model.category.Category
import com.app.compose.util.ApiState
import com.app.compose.util.DispatchersProvider
import com.app.compose.util.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryDataSource: CategoryDataSource,
    private val dispatchersProvider: DispatchersProvider
) : CategoryRepository {
    override suspend fun getCategories(): Flow<ApiState<List<Category>>> {
        return flow {
            emit(ApiState.Loading)
            val result = safeApiCall { categoryDataSource.getCategories() }
            emit(result)
        }.flowOn(dispatchersProvider.io())
    }
}
