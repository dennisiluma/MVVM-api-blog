package com.decagon.android.sq007.utilities.api

import com.decagon.android.sq007.data.Comment
import com.decagon.android.sq007.data.MyPost
import com.decagon.android.sq007.data.Post
import retrofit2.Response
import retrofit2.http.*

interface JsonPlaceHolderApi {
    //used to send a get request and receive a response of Post
    @GET("posts/1")
    suspend fun getPost() : Response<Post>

    //get post by its PostId. same as above but dynamic
    @GET("posts/{postId}")
    suspend fun getPost2(@Path("postId") postId: Int) : Response<Post>

    //get all post by user id
    @GET("posts")
    suspend fun getUserIdPosts(
        @Query("userId") userId:Int): Response<List<Post>>

    //multiple post query
    @GET("posts")
    suspend fun getMultipleQueryPosts(
        @Query("userId") userId: Int?,
        //pass multiple query at once using HashMap instead of using @Query multiple times
        @QueryMap options: Map<String, String>
    ): Response<List<Post>>

    //get comments Related to post for apI
    @GET("comments")
    suspend fun getCommentsForAPost(
        @Query("postId") postId: Int
    ):Response<List<Comment>>

    //post comment to api
    @POST("comments")
    suspend fun postYourComment(@Body post: Comment):Response<Comment>

    //send a post article
    @POST("posts")
    suspend fun pushMyPost(
        @Body post: MyPost
    ): Response<MyPost>
}