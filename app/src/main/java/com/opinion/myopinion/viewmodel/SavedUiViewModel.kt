package com.opinion.myopinion.viewmodel

import androidx.lifecycle.*
import com.opinion.myopinion.models.Opinion
import kotlinx.coroutines.launch

class SavedUiViewModel : ViewModel() {

    private val opinionLiveData = MutableLiveData<List<Opinion>>()

    init {
        getOpinions()
    }

    private fun getOpinions() = viewModelScope.launch {
        // TODO: 30.07.2021 FIX OPINION CACHE
    }

    fun getOpinionsLiveData(): MutableLiveData<List<Opinion>> {
        return opinionLiveData
    }
}