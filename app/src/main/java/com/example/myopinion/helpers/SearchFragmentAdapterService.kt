package com.example.myopinion.helpers

import com.example.myopinion.models.Opinion

interface SearchFragmentAdapterService {

    fun initAdapter()

    fun notifyDataSetChanged()

    fun updateList(filteredList: List<Opinion>)
}