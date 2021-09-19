package com.example.architecturetask9.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.architecturetask9.model.CommentsItem
import com.example.architecturetask9.model.PostListsItem
import com.example.architecturetask9.network.RetroInstance
import com.example.architecturetask9.network.RetroService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.net.UnknownHostException

class CreateCommentViewModel: ViewModel() {
    var commentLiveData: MutableLiveData<Response<CommentsItem>> = MutableLiveData()

    //This function returns the detailsLiveData
    fun createPostObserver(): MutableLiveData<Response<CommentsItem>> {
        return commentLiveData
    }

    fun commentDetails(comment: CommentsItem) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val retroInstance =
                    RetroInstance.getRetroInstance().create(RetroService::class.java)
                val response = retroInstance.createComment(comment)
                commentLiveData.postValue(response)
            } catch (e: UnknownHostException) {
                e.printStackTrace()
            }
        }
    }
}