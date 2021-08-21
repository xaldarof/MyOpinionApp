package com.opinion.myopinion.netRes

import androidx.lifecycle.MutableLiveData
import com.opinion.myopinion.models.Opinion

interface ViewCloudDataSourceService {
    fun getViewsCount(opinion: Opinion): MutableLiveData<List<String>>
}