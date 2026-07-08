package com.app.compose.domain.repository

import com.app.compose.data.remote.model.login.LoginReq
import com.app.compose.data.remote.model.login.LoginResponse
import com.app.compose.util.ApiState
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(loginReq: LoginReq) : Flow<ApiState<LoginResponse>>
}