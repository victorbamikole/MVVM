package com.example.architecturetask9.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.architecturetask9.model.CommentsItem
import com.example.architecturetask9.model.PostListsItem
import com.example.architecturetask9.model.UserResponse
import com.example.architecturetask9.network.RetroInstance
import com.example.architecturetask9.network.RetroService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.UnknownHostException

class CreatePostViewModel : ViewModel() {

    var postLiveData: MutableLiveData<Response<PostListsItem>> = MutableLiveData()

    //This function returns the detailsLiveData
    fun createPostObserver(): MutableLiveData<Response<PostListsItem>> {
        return postLiveData
    }


    //This function is responsible of making Api call to our Api server in IO instead of main thread
    fun postDetails(post: PostListsItem) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val retroInstance =
                    RetroInstance.getRetroInstance().create(RetroService::class.java)
                val response = retroInstance.createPost(post)
                postLiveData.postValue(response)
            } catch (e: UnknownHostException) {
                e.printStackTrace()
            }
        }
    }

}

