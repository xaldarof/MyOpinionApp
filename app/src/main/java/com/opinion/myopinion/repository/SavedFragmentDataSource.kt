package com.opinion.myopinion.repository

class SavedFragmentDataSource(private val savedFragmentCacheDataSource: SavedFragmentCacheDataSource) : SavedFragmentCacheService {
    override fun initAdapter() {
        savedFragmentCacheDataSource.initAdapter()
    }
}