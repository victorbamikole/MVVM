package com.example.architecturetask9.network

import com.example.architecturetask9.model.CommentsItem
import com.example.architecturetask9.model.PostListsItem
import com.example.architecturetask9.model.Posts
import com.example.architecturetask9.model.UserResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


//This is the RetroService Interface to get pokemon from the api
interface RetroService {

    //    @GET("pokemon?limit=11188&offset=0")
    @GET("posts")
    //This function queries our Api and returns the data class
//    suspend fun getDataFromApi(@Query("q") query: String): RecyclerListData
    suspend fun getDataFromApi(): List<PostListsItem>

    @GET("posts")
    //This function queries our Api and returns the data class
    //'https://jsonplaceholder.typicode.com/posts?userId=1'
//    suspend fun getDataFromApi(@Query("q") query: String): RecyclerListData
    suspend fun filterPost(@Query ("userId") searchText: Int): List<PostListsItem>

    @GET("posts/{comId}/comments")
    //This function queries our Api and returns the data class
    //'https://jsonplaceholder.typicode.com/posts?userId=1'
//    suspend fun getDataFromApi(@Query("q") query: String): RecyclerListData
    suspend fun getComments(@Path("comId") comment: Int): List<CommentsItem>

    @GET("posts")
    //This function queries our Api and returns the data class
    //https://jsonplaceholder.typicode.com/posts/2/comments
//    suspend fun getDataFromApi(@Query("q") query: String): RecyclerListData
    suspend fun postComments(@Query ("userId") searchText: Int): List<PostListsItem>

    @POST("posts")
    @Headers("'Content-type': 'application/json; charset=UTF-8'")
    //This function queries our Api and returns the data class
    //'https://jsonplaceholder.typicode.com/posts'
//    suspend fun getDataFromApi(@Query("q") query: String): RecyclerListData
    suspend fun createPost(@Body post: PostListsItem): Response<PostListsItem>

    @POST("/comments")
    @Headers("'Content-type': 'application/json; charset=UTF-8'")
    //This function queries our Api and returns the data class
    //'https://jsonplaceholder.typicode.com/posts'
//    suspend fun getDataFromApi(@Query("q") query: String): RecyclerListData
    suspend fun createComment(@Body post: CommentsItem): Response<CommentsItem>

    @POST("posts/{id}/comments")
    @Headers("'Content-type': 'application/json; charset=UTF-8'")
    //This function queries our Api and returns the data class
    //'https://jsonplaceholder.typicode.com/posts'
//    suspend fun getDataFromApi(@Query("q") query: String): RecyclerListData
    suspend fun createNewComment(@Path ("id")comId: Int): Response<CommentsItem>

}