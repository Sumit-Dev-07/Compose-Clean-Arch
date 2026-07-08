package com.app.compose.domain.repository

import com.app.compose.data.remote.model.login.LoginRequest
import com.app.compose.data.remote.model.login.LoginResponse
import com.app.compose.util.ApiState
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(loginReq: LoginRequest) : Flow<ApiState<LoginResponse>>
}