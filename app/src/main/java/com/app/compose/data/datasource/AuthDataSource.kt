package com.app.compose.data.datasource

import com.app.compose.data.remote.model.login.LoginReq
import com.app.compose.data.remote.model.login.LoginResponse
import retrofit2.Response

interface AuthDataSource {
    suspend fun login(loginReq: LoginReq): Response<LoginResponse>
}