package com.app.compose.data.remote

import com.app.compose.data.remote.model.login.LoginReq
import com.app.compose.data.remote.model.login.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST(ApiPath.LOGIN)
    suspend fun login(
        @Body request: LoginReq
    ): Response<LoginResponse>

}