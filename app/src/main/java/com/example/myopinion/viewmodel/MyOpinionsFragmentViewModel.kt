package com.example.myopinion.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myopinion.repository.entity.*
import com.example.myopinion.repository.OpinionCacheDataSource
import com.example.myopinion.repository.OpinionDataSource
import io.realm.Realm
import io.realm.RealmResults

class MyOpinionsFragmentViewModel(private val realm: Realm) : ViewModel() {

    private val opinionDataSource = OpinionDataSource(OpinionCacheDataSource(realm))

    fun getOpinionsFromDatabase(): RealmResults<OpinionEntity> {
        return opinionDataSource.getOpinions()
    }

    fun saveOpinionToDatabase(opinionEntity: OpinionEntity){
        opinionDataSource.saveOpinion(opinionEntity)
    }
}