package com.decagon.android.sq007.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decagon.android.sq007.data.MyPost
import com.decagon.android.sq007.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MyCustomPostViewModel(private val repository: Repository): ViewModel() {

    val myCustomPost: MutableLiveData<Response<MyPost>> = MutableLiveData()

    fun pushMyPost(mypost: MyPost){
        viewModelScope.launch {
            val response = repository.pushMyPost(mypost)
            myCustomPost.value=response
        }
    }
}