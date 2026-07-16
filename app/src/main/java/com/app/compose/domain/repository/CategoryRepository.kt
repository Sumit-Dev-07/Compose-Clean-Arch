package com.app.compose.domain.repository

import com.app.compose.data.remote.model.category.Category
import com.app.compose.util.ApiState
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun getCategories(): Flow<ApiState<List<Category>>>
}
