package com.manyan.mystoryapp.presentation.story

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manyan.mystoryapp.dataApp.remote.ApiResponse
import com.manyan.mystoryapp.dataApp.remote.story.AddStoriesResponse
import com.manyan.mystoryapp.dataApp.remote.story.GetStoriesResponse
import com.manyan.mystoryapp.dataApp.repository.StoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class StoryViewModel @Inject constructor(private val storyRepository: StoryRepository): ViewModel() {

    fun getAllStories(token: String) : LiveData<ApiResponse<GetStoriesResponse>> {
        val result = MutableLiveData<ApiResponse<GetStoriesResponse>>()
        viewModelScope.launch {
            storyRepository.getAllStories(token).collect {
                result.postValue(it)
            }
        }
        return result
    }

    fun addNewStory(token: String, file: MultipartBody.Part, description: RequestBody): LiveData<ApiResponse<AddStoriesResponse>> {
        val result = MutableLiveData<ApiResponse<AddStoriesResponse>>()
        viewModelScope.launch {
            storyRepository.addNewStory(token, file, description).collect {
                result.postValue(it)
            }
        }
        return result
    }

}