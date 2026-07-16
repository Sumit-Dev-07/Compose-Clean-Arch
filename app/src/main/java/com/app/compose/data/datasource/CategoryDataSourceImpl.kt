package com.app.compose.data.datasource

import com.app.compose.data.remote.ApiService
import com.app.compose.data.remote.model.category.Category
import retrofit2.Response
import javax.inject.Inject

class CategoryDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : CategoryDataSource {
    override suspend fun getCategories(): Response<List<Category>> {
        return apiService.getCategories()
    }
}
