package com.example.myopinion.repository

import com.example.myopinion.repository.entity.FavoriteOpinionEntity
import io.realm.RealmResults

class FavoriteOpinionDataSource(private val favoriteOpinionCacheDataSource: FavoriteOpinionCacheDataSource): FavoriteOpinionCacheService {
    override fun saveOpinionToFavorites(favoriteOpinionEntity: FavoriteOpinionEntity){
        favoriteOpinionCacheDataSource.saveOpinionToFavorites(favoriteOpinionEntity)
    }

    override fun getFavoriteOpinions(): RealmResults<FavoriteOpinionEntity> {
        return favoriteOpinionCacheDataSource.getFavoriteOpinions()
    }

    override fun deleteFavoriteOpinion(opinionEntity: FavoriteOpinionEntity, position: Int) {
        favoriteOpinionCacheDataSource.deleteFavoriteOpinion(opinionEntity,position)
    }
}