package com.ahrybuk.swivltest.presentation.userdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UserDetailViewModelProvider(private val username: String) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(UserDetailViewModel::class.java)) {
            UserDetailViewModel(username) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}