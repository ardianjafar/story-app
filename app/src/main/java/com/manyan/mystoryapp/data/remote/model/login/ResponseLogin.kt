package com.manyan.mystoryapp.data.remote.model.login

import com.google.gson.annotations.SerializedName

data class ResponseLogin(
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("loginResult")
    val result: Result

)