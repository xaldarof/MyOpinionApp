package com.example.myopinion.netRes

import androidx.lifecycle.MutableLiveData
import com.example.myopinion.models.Opinion
import com.example.myopinion.repository.OpinionCacheService

class OpinionsDataSource(private val opinionsService: OpinionsService) {

    fun getOpinions(): MutableLiveData<List<Opinion>> {
        return opinionsService.getOpinions()
    }
}