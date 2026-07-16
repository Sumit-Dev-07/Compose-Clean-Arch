package com.app.compose.data.remote

import com.app.compose.data.remote.model.login.LoginRequest
import com.app.compose.data.remote.model.login.LoginResponse
import com.app.compose.data.remote.model.product.ProductResponse
import com.app.compose.data.remote.model.category.Category
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
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

    @GET(ApiPath.CATEGORIES)
    suspend fun getCategories(): Response<List<Category>>

    @GET("${ApiPath.PRODUCTS_BY_CATEGORY}{category}")
    suspend fun getProductsByCategory(
        @Path("category") category: String
    ): Response<ProductResponse>
}