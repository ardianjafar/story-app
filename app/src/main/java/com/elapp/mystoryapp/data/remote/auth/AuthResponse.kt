package com.elapp.mystoryapp.data.remote.auth

import com.elapp.mystoryapp.data.model.User
import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("loginResult")
    val loginResult: User
)