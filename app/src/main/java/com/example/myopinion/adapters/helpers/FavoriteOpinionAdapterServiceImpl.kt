package com.example.myopinion.adapters.helpers

class FavoriteOpinionAdapterServiceImpl(private val favoriteFragmentAdapterHelper: FavoriteFragmentAdapterServiceHelper) :
    FavoriteFragmentAdapterService {
    override fun initAdapter() {
        favoriteFragmentAdapterHelper.initAdapter()
    }

    override fun notifyDataSetChanged(position:Int) {
        favoriteFragmentAdapterHelper.notifyDataSetChanged(position)
    }
}