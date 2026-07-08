package com.app.compose.domain.repository

import com.app.compose.data.datasource.AuthDataSource
import com.app.compose.data.remote.model.login.LoginReq
import com.app.compose.data.remote.model.login.LoginResponse
import com.app.compose.util.ApiState
import com.app.compose.util.DispatchersProvider
import com.app.compose.util.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val dispatchersProvider: DispatchersProvider
) : AuthRepository {
    override suspend fun login(loginReq: LoginReq): Flow<ApiState<LoginResponse>> {
        return flow {
            val result = safeApiCall { authDataSource.login(loginReq) }
            emit(result)
        }.flowOn(dispatchersProvider.io())
    }
}