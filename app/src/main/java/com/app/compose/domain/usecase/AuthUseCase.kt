package com.app.compose.domain.usecase

import com.app.compose.data.remote.model.login.LoginReq
import com.app.compose.data.remote.model.login.LoginResponse
import com.app.compose.domain.repository.AuthRepository
import com.app.compose.util.ApiState
import com.app.compose.util.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend fun login(loginReq: LoginReq): Flow<UiState<LoginResponse>> {
        return authRepository.login(loginReq).map { result ->
            when (result) {
                is ApiState.Success -> UiState.Success(result.data)
                is ApiState.Error -> UiState.Error(result.message)
            }
        }
    }
}