package com.manyan.mystoryapp.dataApp.repository

import com.manyan.mystoryapp.dataApp.remote.ApiResponse
import com.manyan.mystoryapp.dataApp.remote.auth.AuthBody
import com.manyan.mystoryapp.dataApp.remote.auth.AuthResponse
import com.manyan.mystoryapp.dataApp.remote.auth.LoginBody
import com.manyan.mystoryapp.dataApp.source.AuthDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(private val authDataSource: AuthDataSource) {

    suspend fun registerUser(authBody: AuthBody): Flow<ApiResponse<Response<AuthResponse>>> {
            return authDataSource.registerUser(authBody).flowOn(Dispatchers.IO)
    }

    suspend fun loginUser(loginBody: LoginBody): Flow<ApiResponse<AuthResponse>> {
        return authDataSource.loginUser(loginBody).flowOn(Dispatchers.IO)
    }

}