package com.manyan.mystoryapp.view.dashboard.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.manyan.mystoryapp.data.Repository
import com.manyan.mystoryapp.model.StoryModel


class HomeViewModel(private val userRepository : Repository) : ViewModel() {

    lateinit var userStories: LiveData<PagingData<StoryModel>>

    private var _message = MutableLiveData<String>()
    val message: LiveData<String> = _message


    fun getUserToken() = userRepository.getUserToken()

    fun getName(): LiveData<String> {
        return userRepository.getUserName()
    }

    fun getUserStories(token: String) {
        userStories = userRepository.getUserStoriesList(token).cachedIn(viewModelScope)
    }
}