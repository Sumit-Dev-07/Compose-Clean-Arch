package com.app.compose.data.datasource

import com.app.compose.data.remote.model.category.Category
import retrofit2.Response

interface CategoryDataSource {
    suspend fun getCategories(): Response<List<Category>>
}
