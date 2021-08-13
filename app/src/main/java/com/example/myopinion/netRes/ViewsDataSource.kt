package com.example.myopinion.netRes

import androidx.lifecycle.MutableLiveData
import com.example.myopinion.models.Opinion

class ViewsDataSource(private val viewsCloudDataSource: ViewsCloudDataSource) :ViewCloudDataSourceService {
    override fun getViewsCount(opinion: Opinion): MutableLiveData<List<String>> {
       return viewsCloudDataSource.getViewsCount(opinion)
    }
}