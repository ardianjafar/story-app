package com.manyan.mystoryapp.dataApp.remote.story

import com.google.gson.annotations.SerializedName

data class AddStoriesResponse(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String
)