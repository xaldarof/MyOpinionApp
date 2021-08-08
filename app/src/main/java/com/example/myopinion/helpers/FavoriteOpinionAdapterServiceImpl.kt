package com.example.myopinion.helpers

class FavoriteOpinionAdapterServiceImpl(private val favoriteFragmentAdapterHelper: FavoriteFragmentAdapterServiceHelper) : FavoriteFragmentAdapterService {
    override fun initAdapter() {
        favoriteFragmentAdapterHelper.initAdapter()
    }

    override fun notifyDataSetChanged() {
        favoriteFragmentAdapterHelper.notifyDataSetChanged()
    }
}