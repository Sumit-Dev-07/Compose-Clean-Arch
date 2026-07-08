package com.app.compose.data.datasource

import com.app.compose.data.remote.ApiService
import com.app.compose.data.remote.model.login.LoginReq
import com.app.compose.data.remote.model.login.LoginResponse
import retrofit2.Response
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(private var apiService: ApiService) : AuthDataSource {
    override suspend fun login(loginReq: LoginReq): Response<LoginResponse> {
        return apiService.login(loginReq)
    }
}