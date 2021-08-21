package com.opinion.myopinion.viewmodel

import androidx.lifecycle.MutableLiveData
import com.opinion.myopinion.repository.entity.FavoriteOpinionEntity
import io.realm.RealmResults

interface SavedFragmentViewModelService {
    fun initAdapter()
    fun getDataForCheck(): MutableLiveData<RealmResults<FavoriteOpinionEntity>>
}