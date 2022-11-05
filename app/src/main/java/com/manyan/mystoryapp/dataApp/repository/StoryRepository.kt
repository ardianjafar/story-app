package com.manyan.mystoryapp.dataApp.repository

import com.manyan.mystoryapp.dataApp.remote.ApiResponse
import com.manyan.mystoryapp.dataApp.remote.story.AddStoriesResponse
import com.manyan.mystoryapp.dataApp.remote.story.GetStoriesResponse
import com.manyan.mystoryapp.dataApp.source.StoryDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StoryRepository @Inject constructor(private val storyDataSource: StoryDataSource) {

    suspend fun getAllStories(token: String): Flow<ApiResponse<GetStoriesResponse>> {
        return storyDataSource.getAllStories(token).flowOn(Dispatchers.IO)
    }

    suspend fun addNewStory(token: String, file: MultipartBody.Part, description: RequestBody): Flow<ApiResponse<AddStoriesResponse>> {
        return storyDataSource.addNewStory(token, file, description)
    }

}