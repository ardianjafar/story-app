package com.manyan.mystoryapp.viewmodel

import androidx.lifecycle.ViewModel
import com.manyan.mystoryapp.data.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(private val dataRepository: DataRepository) : ViewModel() {
    suspend fun getStoriesLocation(auth: String) = dataRepository.getStoriesLocation(auth)
}