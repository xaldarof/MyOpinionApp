package com.example.myopinion.repository

import com.example.myopinion.repository.entity.FavoriteOpinionEntity
import io.realm.Realm
import io.realm.RealmResults

class FavoriteOpinionCacheDataSource(private var realm: Realm) : FavoriteOpinionCacheService {

    override fun saveOpinionToFavorites(favoriteOpinionEntity: FavoriteOpinionEntity) {
        realm = Realm.getDefaultInstance()
        realm.executeTransaction {
            it.insertOrUpdate(favoriteOpinionEntity)
        }
    }

    override fun getFavoriteOpinions(): RealmResults<FavoriteOpinionEntity> {
        return realm.where(FavoriteOpinionEntity::class.java).findAll()
    }
}