package com.decagon.android.sq007.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decagon.android.sq007.data.Post
import com.decagon.android.sq007.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainActivityViewModel(private val repository: Repository):ViewModel() {

    val myResponse:MutableLiveData<Response<Post>> = MutableLiveData()
    val myResponse2:MutableLiveData<Response<Post>> = MutableLiveData()
    val myResponseByUserId:MutableLiveData<Response<List<Post>>> = MutableLiveData()
    val myMultiQueryPost:MutableLiveData<Response<List<Post>>> = MutableLiveData()

    fun getPost(){
        viewModelScope.launch {
            val response = repository.getPost()
            myResponse.value = response

        }
    }
    fun getPost2(postId:Int){
        viewModelScope.launch {
            val response2 = repository.getPost2(postId)
            myResponse2.value = response2
        }
    }
    fun getAllPostOfUserId(userId:Int){
        viewModelScope.launch {
            val responseUserId = repository.getAlPostByUserId(userId)
            myResponseByUserId.setValue(responseUserId)
        }
    }

    fun getMultiQueryPost(userId: Int?, options:Map<String, String>){
        viewModelScope.launch {
            val multiQueryPost = repository.getMultiplrQueryPosts(userId,options)
            myMultiQueryPost.value = multiQueryPost
        }
    }

}