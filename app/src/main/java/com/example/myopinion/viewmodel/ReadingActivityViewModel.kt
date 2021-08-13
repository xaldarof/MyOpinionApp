package com.example.myopinion.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myopinion.models.Opinion
import com.example.myopinion.netRes.ViewsCloudDataSource
import com.example.myopinion.netRes.ViewsDataSource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.launch

class ReadingActivityViewModel(firebaseDatabase: FirebaseDatabase,
                               firebaseAuth: FirebaseAuth,private val opinion:Opinion) : ViewModel () {

    private val viewsDataSource = ViewsDataSource(ViewsCloudDataSource(firebaseDatabase,firebaseAuth))
    private val viewsLiveData = MutableLiveData<List<String>>()

    init {
        getViewsCount()
    }

    private fun getViewsCount() = viewModelScope.launch {
        viewsDataSource.getViewsCount(opinion).observeForever {
            viewsLiveData.postValue(it)
        }
    }

    fun getViewCountLiveData():MutableLiveData<List<String>> {
        return viewsLiveData
    }
}