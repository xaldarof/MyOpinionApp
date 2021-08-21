package com.opinion.myopinion.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.opinion.myopinion.models.Opinion
import com.opinion.myopinion.netRes.OpinionsCloudDataSource
import com.opinion.myopinion.netRes.OpinionsDataSource
import com.opinion.myopinion.repository.Result
import com.google.firebase.FirebaseException
import kotlinx.coroutines.launch

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

    fun getLiveData(): MutableLiveData<Result<List<Opinion>>> = opinionsLiveData
}
