package com.manyan.mystoryapp.dataApp.remote.auth

import com.manyan.mystoryapp.dataApp.model.User
import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("loginResult")
    val loginResult: User
)