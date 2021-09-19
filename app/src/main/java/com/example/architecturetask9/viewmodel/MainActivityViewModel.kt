package com.example.architecturetask9.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.architecturetask9.model.PostListsItem
import com.example.architecturetask9.model.Posts
import com.example.architecturetask9.network.RetroInstance
import com.example.architecturetask9.network.RetroService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.UnknownHostException

//This is the MainActivity View Model class
class MainActivityViewModel : ViewModel() {

    // Holds the details of the pokemon retrieved from the API
    var recyclerListLiveData: MutableLiveData<List<PostListsItem>> = MutableLiveData()


    //This function returns the recyclerListLiveData
    fun getRecyclerListObserver(): MutableLiveData<List<PostListsItem>> {
        return recyclerListLiveData
    }

    //This function is responsible of making Api call to our Api server in IO instead of main thread
    fun makeApiCall() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val retroInstance =
                    RetroInstance.getRetroInstance().create(RetroService::class.java)
                val response = retroInstance.getDataFromApi()
                recyclerListLiveData.postValue(response)
            } catch (e: UnknownHostException) {
                e.printStackTrace()
            }
        }
    }

    fun searchPosts(idd: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val retroInstance =
                    RetroInstance.getRetroInstance().create(RetroService::class.java)
                val response = retroInstance.filterPost(idd)
                recyclerListLiveData.postValue(response)
            } catch (e: UnknownHostException) {
                e.printStackTrace()
            }
        }
    }
}