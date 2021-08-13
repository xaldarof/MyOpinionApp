package com.example.myopinion.netRes

import androidx.lifecycle.MutableLiveData
import com.example.myopinion.models.Opinion

interface ViewCloudDataSourceService {
    fun getViewsCount(opinion: Opinion): MutableLiveData<List<String>>
}