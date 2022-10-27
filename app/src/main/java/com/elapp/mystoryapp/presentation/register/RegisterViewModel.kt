package com.elapp.mystoryapp.presentation.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elapp.mystoryapp.data.remote.ApiResponse
import com.elapp.mystoryapp.data.remote.auth.AuthBody
import com.elapp.mystoryapp.data.remote.auth.AuthResponse
import com.elapp.mystoryapp.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val authRepository: AuthRepository): ViewModel() {

    fun registerUser(authBody: AuthBody): LiveData<ApiResponse<Response<AuthResponse>>> {
        val result = MutableLiveData<ApiResponse<Response<AuthResponse>>>()
        viewModelScope.launch {
            authRepository.registerUser(authBody).collect {
                result.postValue(it)
            }
        }
        return result
    }

}