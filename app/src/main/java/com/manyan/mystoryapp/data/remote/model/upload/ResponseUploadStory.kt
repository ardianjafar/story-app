package com.manyan.mystoryapp.data.remote.model.upload

import com.google.gson.annotations.SerializedName

data class ResponseUploadStory(
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String
)
