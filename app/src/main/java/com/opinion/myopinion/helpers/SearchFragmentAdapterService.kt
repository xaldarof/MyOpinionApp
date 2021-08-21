package com.opinion.myopinion.helpers

import com.opinion.myopinion.models.Opinion

interface SearchFragmentAdapterService {

    fun initAdapter()

    fun notifyDataSetChanged()

    fun updateList(filteredList: List<Opinion>)
}