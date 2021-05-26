package com.decagon.android.sq007.repository

import com.decagon.android.sq007.data.Comment
import com.decagon.android.sq007.data.MyPost
import com.decagon.android.sq007.data.Post
import com.decagon.android.sq007.utilities.api.RetofitInstance
import retrofit2.Response
import retrofit2.Retrofit

class Repository {
    suspend fun getPost():Response<Post>{
        return RetofitInstance.api.getPost()
    }
    suspend fun getPost2(postId:Int) : Response<Post>{
        return RetofitInstance.api.getPost2(postId)
    }
    suspend fun getAlPostByUserId(userId:Int):Response<List<Post>>{
        return RetofitInstance.api.getUserIdPosts(userId)
    }

    suspend fun getMultiplrQueryPosts(userId: Int?, options:Map<String, String>):Response<List<Post>>{
        return RetofitInstance.api.getMultipleQueryPosts(userId, options)
    }
    suspend fun getCommentsForASinglePost(postId: Int):Response<List<Comment>>{
        return RetofitInstance.api.getCommentsForAPost(postId)
    }
    suspend fun pushComment(comment: Comment):Response<Comment>{
        return RetofitInstance.api.postYourComment(comment)
    }

    suspend fun pushMyPost(mypost: MyPost):Response<MyPost>{
        return RetofitInstance.api.pushMyPost(mypost)
    }
}