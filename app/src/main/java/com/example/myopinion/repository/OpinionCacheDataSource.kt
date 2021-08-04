package com.example.myopinion.repository

import com.example.myopinion.repository.entity.OpinionEntity
import io.realm.Realm
import io.realm.RealmResults

class OpinionCacheDataSource(private var realm: Realm) : OpinionCacheService {
    override fun saveOpinion(opinion: OpinionEntity) {
        realm = Realm.getDefaultInstance()
        realm.executeTransaction {
            it.insertOrUpdate(opinion)
        }
    }

    override fun getOpinions(): RealmResults<OpinionEntity> {
        return realm.where(OpinionEntity::class.java).findAll()
    }


}