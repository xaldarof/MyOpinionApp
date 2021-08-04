package com.example.myopinion.repository

import com.example.myopinion.repository.entity.OpinionEntity
import io.realm.RealmResults

class OpinionDataSource(private val opinionCacheDataSource: OpinionCacheDataSource): OpinionCacheService {
    override fun saveOpinion(opinion: OpinionEntity) {
        opinionCacheDataSource.saveOpinion(opinion)
    }

    override fun getOpinions(): RealmResults<OpinionEntity> {
        return opinionCacheDataSource.getOpinions()
    }
}