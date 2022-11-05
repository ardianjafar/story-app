package com.manyan.mystoryapp.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manyan.mystoryapp.dataApp.remote.ApiResponse
import com.manyan.mystoryapp.dataApp.remote.auth.AuthResponse
import com.manyan.mystoryapp.dataApp.remote.auth.LoginBody
import com.manyan.mystoryapp.dataApp.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepository: AuthRepository): ViewModel() {

    fun loginUser(loginBody: LoginBody): LiveData<ApiResponse<AuthResponse>> {
        val result = MutableLiveData<ApiResponse<AuthResponse>>()
        viewModelScope.launch {
            authRepository.loginUser(loginBody).collect {
                result.postValue(it)
            }
        }
        return result
    }

}