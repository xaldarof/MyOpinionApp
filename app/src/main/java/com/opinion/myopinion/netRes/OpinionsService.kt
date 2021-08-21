package com.opinion.myopinion.netRes

import androidx.lifecycle.MutableLiveData
import com.opinion.myopinion.models.Opinion

interface OpinionsService {
    fun getOpinions(): MutableLiveData<List<Opinion>>
}