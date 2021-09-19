package com.example.architecturetask9.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.architecturetask9.model.Comments
import com.example.architecturetask9.model.CommentsItem
import com.example.architecturetask9.model.PostListsItem
import com.example.architecturetask9.network.RetroInstance
import com.example.architecturetask9.network.RetroService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailPostViewModel : ViewModel() {

    // This variable Holds the details of the pokemon retrieved from the API
    var commentsLiveData: MutableLiveData<List<CommentsItem>> = MutableLiveData()

    //This function returns the detailsLiveData
    fun getRecyclerListObserver2(): MutableLiveData<List<CommentsItem>> {
        return commentsLiveData
    }


    //This function is responsible of making Api call to our Api server in IO instead of main thread
    fun callComments(id: Int) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                val retroInstance =
                    RetroInstance.getRetroInstance().create(RetroService::class.java)
                val response = retroInstance.getComments(id)
                commentsLiveData.postValue(response)
            }
        } catch (e: Exception) {

        }
    }
}