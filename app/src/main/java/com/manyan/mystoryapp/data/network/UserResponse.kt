package com.manyan.mystoryapp.data.network

import com.manyan.mystoryapp.model.StoryModel
import com.manyan.mystoryapp.model.UserModel
import com.google.gson.annotations.SerializedName


data class UserResponse(

    @field:SerializedName("message")
	val message: String? = null,

    @field:SerializedName("loginResult")
	val loginResult: UserModel? = null,

    @field:SerializedName("listStory")
	val listStory: ArrayList<StoryModel>? = null,

    )
