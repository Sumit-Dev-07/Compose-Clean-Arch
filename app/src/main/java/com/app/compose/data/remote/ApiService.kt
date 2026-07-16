package com.app.compose.data.remote

import com.app.compose.data.remote.model.login.LoginRequest
import com.app.compose.data.remote.model.login.LoginResponse
import com.app.compose.data.remote.model.product.ProductResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST(ApiPath.LOGIN)
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>

    @GET(ApiPath.PRODUCTS)
    suspend fun getProducts(
        @Query("limit") limit: Int,
        @Query("skip") skip: Int
    ): Response<ProductResponse>
}