package com.opinion.myopinion.netRes

import androidx.lifecycle.MutableLiveData
import com.opinion.myopinion.models.Opinion

class OpinionsDataSource(private val opinionsService: OpinionsService) {

    fun getOpinions(): MutableLiveData<List<Opinion>> {
        return opinionsService.getOpinions()
    }
}