package com.opinion.myopinion.netRes

import androidx.lifecycle.MutableLiveData
import com.opinion.myopinion.models.Opinion

class ViewsDataSource(private val viewsCloudDataSource: ViewsCloudDataSource) :ViewCloudDataSourceService {
    override fun getViewsCount(opinion: Opinion): MutableLiveData<List<String>> {
       return viewsCloudDataSource.getViewsCount(opinion)
    }
}