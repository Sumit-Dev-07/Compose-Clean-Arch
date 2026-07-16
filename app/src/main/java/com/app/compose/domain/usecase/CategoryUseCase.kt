package com.app.compose.domain.usecase

import com.app.compose.data.remote.model.category.Category
import com.app.compose.domain.repository.CategoryRepository
import com.app.compose.util.ApiState
import com.app.compose.util.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryUseCase @Inject constructor(private val categoryRepository: CategoryRepository) {
    suspend fun getCategories(): Flow<UiState<List<Category>>> {
        return categoryRepository.getCategories().map { result ->
            when (result) {
                is ApiState.Loading -> UiState.Loading
                is ApiState.Success -> UiState.Success(result.data)
                is ApiState.Error -> UiState.Error(result.message)
            }
        }
    }
}
