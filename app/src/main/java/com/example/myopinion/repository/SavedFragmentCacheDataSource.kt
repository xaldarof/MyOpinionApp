package com.example.myopinion.repository

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.myopinion.adapters.helpers.FavoriteFragmentAdapterServiceHelper
import com.example.myopinion.adapters.helpers.FavoriteOpinionAdapterServiceImpl
import com.example.myopinion.helpers.BundleSender
import com.example.myopinion.helpers.BundleSenderProvider
import io.realm.Realm

class SavedFragmentCacheDataSource(private val realm: Realm,private val activity: Activity,private val fragment:Fragment,private val recyclerView: RecyclerView) : SavedFragmentCacheService {

    override fun initAdapter() {
        val favoriteOpinionDataSource = FavoriteOpinionDataSource(FavoriteOpinionCacheDataSource(realm))
        val bundleSender = BundleSender(BundleSenderProvider())
        val list = favoriteOpinionDataSource.getFavoriteOpinions()

        val favoriteOpinionAdapterImpl = FavoriteOpinionAdapterServiceImpl(
            FavoriteFragmentAdapterServiceHelper(favoriteOpinionDataSource,
                list,bundleSender,activity,fragment,recyclerView))
        favoriteOpinionAdapterImpl.initAdapter()
    }
}