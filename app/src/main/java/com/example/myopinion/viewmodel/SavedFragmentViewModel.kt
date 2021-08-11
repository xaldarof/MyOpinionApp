package com.example.myopinion.viewmodel

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.myopinion.repository.FavoriteOpinionCacheDataSource
import com.example.myopinion.repository.FavoriteOpinionDataSource
import com.example.myopinion.repository.SavedFragmentCacheDataSource
import com.example.myopinion.repository.SavedFragmentDataSource
import com.example.myopinion.repository.entity.FavoriteOpinionEntity
import io.realm.Realm
import io.realm.RealmResults

class SavedFragmentViewModel(activity: Activity,fragment:Fragment,recyclerView: RecyclerView,realm: Realm) : ViewModel(),SavedFragmentViewModelService {

    private val savedFragmentDataSource = SavedFragmentDataSource(SavedFragmentCacheDataSource(realm,activity,fragment,recyclerView))
    private val favoriteOpinionDataSource = FavoriteOpinionDataSource(FavoriteOpinionCacheDataSource(realm))

    override fun initAdapter() {
        savedFragmentDataSource.initAdapter()
    }

    override fun getDataForCheck():MutableLiveData<RealmResults<FavoriteOpinionEntity>>{
        val mutableLiveData = MutableLiveData<RealmResults<FavoriteOpinionEntity>>()
        mutableLiveData.value = favoriteOpinionDataSource.getFavoriteOpinions()

        return mutableLiveData
    }
}