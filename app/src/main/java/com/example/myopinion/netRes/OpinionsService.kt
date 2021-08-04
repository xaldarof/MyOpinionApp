package com.example.myopinion.netRes

import androidx.lifecycle.MutableLiveData
import com.example.myopinion.models.Opinion

interface OpinionsService {
    fun getOpinions(): MutableLiveData<List<Opinion>>
}