package com.app.compose.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.compose.data.remote.model.login.LoginReq
import com.app.compose.data.remote.model.login.LoginResponse
import com.app.compose.domain.usecase.AuthUseCase
import com.app.compose.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {
    private val _userState = MutableStateFlow<UiState<LoginResponse>>(UiState.Loading)
    val userState: StateFlow<UiState<LoginResponse>> = _userState.asStateFlow()

    fun login(loginReq: LoginReq) {
        viewModelScope.launch {
            authUseCase.login(loginReq)
                .onStart { _userState.value = UiState.Loading }
                .catch { error -> _userState.value = UiState.Error("${error.localizedMessage}") }
                .collect { result ->
                    _userState.value = result
                }
        }
    }
}