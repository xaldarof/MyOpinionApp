package com.example.myopinion.repository

import com.example.myopinion.repository.entity.OpinionEntity
import io.realm.RealmResults

interface OpinionCacheService {
    fun saveOpinion(opinion: OpinionEntity)

    fun getOpinions(): RealmResults<OpinionEntity>
}