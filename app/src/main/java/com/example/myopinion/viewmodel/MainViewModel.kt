package com.example.myopinion.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myopinion.models.Opinion
import com.example.myopinion.netRes.OpinionsCloudDataSource
import kotlinx.coroutines.*
import com.example.myopinion.repository.*
import com.example.myopinion.netRes.OpinionsDataSource
import com.google.firebase.FirebaseException

class MainViewModel : ViewModel() {

    private val opinionsLiveData = MutableLiveData<Result<List<Opinion>>>()
    private val opinionsDataSource = OpinionsDataSource(OpinionsCloudDataSource())

    init {
        getOpinions()
    }

    private fun getOpinions() = viewModelScope.launch {
        try {
            opinionsDataSource.getOpinions().observeForever {
                opinionsLiveData.postValue(Result.success(it))
            }
        } catch (e: FirebaseException) {
            opinionsLiveData.postValue(Result.error(null, e.message))
        }
    }

    fun getLiveData(): MutableLiveData<Result<List<Opinion>>> {
        return opinionsLiveData
    }
}