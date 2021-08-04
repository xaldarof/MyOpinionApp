package com.example.myopinion.helpers

import com.example.myopinion.models.Opinion

class SearchAdapter (private val searchFragmentAdapterProvider: SearchFragmentAdapterProvider): SearchFragmentAdapterService {
    override fun initAdapter() {
        searchFragmentAdapterProvider.initAdapter()
    }

    override fun notifyDataSetChanged() {
        searchFragmentAdapterProvider.notifyDataSetChanged()
    }

    override fun updateList(filteredList: List<Opinion>) {
        searchFragmentAdapterProvider.updateList(filteredList)
    }
}