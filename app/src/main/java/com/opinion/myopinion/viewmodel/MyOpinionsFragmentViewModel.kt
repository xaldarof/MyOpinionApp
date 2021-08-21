package com.opinion.myopinion.viewmodel

import androidx.lifecycle.ViewModel
import com.opinion.myopinion.repository.entity.*
import com.opinion.myopinion.repository.OpinionCacheDataSource
import com.opinion.myopinion.repository.OpinionDataSource
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