package com.example.myopinion.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.myopinion.repository.entity.FavoriteOpinionEntity
import io.realm.RealmResults

interface SavedFragmentViewModelService {
    fun initAdapter()
    fun getDataForCheck(): MutableLiveData<RealmResults<FavoriteOpinionEntity>>
}