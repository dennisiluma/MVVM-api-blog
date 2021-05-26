package com.decagon.android.sq007.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.decagon.android.sq007.repository.Repository

class PostDetailActivityViewModelFactory(private val repository: Repository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PostDetailActivityViewModel(repository) as T
    }
}