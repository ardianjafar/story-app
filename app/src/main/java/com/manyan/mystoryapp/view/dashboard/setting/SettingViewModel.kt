package com.manyan.mystoryapp.view.dashboard.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.manyan.mystoryapp.data.Repository

class SettingViewModel(private val userRepository : Repository) : ViewModel() {
    fun getUserName() : LiveData<String> = userRepository.getUserName()
    fun getUserEmail() : LiveData<String> = userRepository.getUserEmail()


}