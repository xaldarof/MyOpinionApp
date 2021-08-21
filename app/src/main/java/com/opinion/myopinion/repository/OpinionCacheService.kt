package com.opinion.myopinion.repository

import com.opinion.myopinion.repository.entity.OpinionEntity
import io.realm.RealmResults

interface OpinionCacheService {
    fun saveOpinion(opinion: OpinionEntity)

    fun getOpinions(): RealmResults<OpinionEntity>
}