package com.example.myopinion.helpers

import com.example.myopinion.models.Opinion

class TextFilterProvider(private val list: List<Opinion>,private val searchAdapter: SearchAdapter):TextFilterService {
    override fun filter(filterString: String) {
        val tempList = ArrayList<Opinion>()
        for (i in list){
            if (filterString in i.shortDescription){
                tempList.add(i)
            }
        }
        searchAdapter.updateList(tempList)
    }
}