package com.decagon.android.sq007.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decagon.android.sq007.data.Comment
import com.decagon.android.sq007.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class PostDetailActivityViewModel(private val repository: Repository):ViewModel() {

    var commentData = MutableLiveData<Response<List<Comment>>>()
    var myaddedComment = MutableLiveData<Response<Comment>>()

    fun getComments(postId:Int){
        viewModelScope.launch {
            val commentResponseBody = repository.getCommentsForASinglePost(postId)
            commentData.value=commentResponseBody
        }
    }

    fun pushComment(comment: Comment){
        viewModelScope.launch {
            val commentPostBody = repository.pushComment(comment)
            myaddedComment.value=commentPostBody
        }
    }
}