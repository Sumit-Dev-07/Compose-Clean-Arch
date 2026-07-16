package com.app.compose.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.compose.data.remote.model.category.Category
import com.app.compose.domain.usecase.CategoryUseCase
import com.app.compose.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val categoryUseCase: CategoryUseCase
) : ViewModel() {

    private val _categoryState = MutableStateFlow<UiState<List<Category>>>(UiState.Idle)
    val categoryState: StateFlow<UiState<List<Category>>> = _categoryState.asStateFlow()

    init {
        getCategories()
    }

    fun getCategories() {
        viewModelScope.launch {
            categoryUseCase.getCategories().collect {
                _categoryState.value = it
            }
        }
    }
}
