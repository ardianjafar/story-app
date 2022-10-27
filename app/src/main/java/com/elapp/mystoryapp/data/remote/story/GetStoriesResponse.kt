package com.elapp.mystoryapp.data.remote.story

import com.elapp.mystoryapp.data.model.Story
import com.google.gson.annotations.SerializedName

data class GetStoriesResponse(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("listStory")
    val listStory: List<Story>
)