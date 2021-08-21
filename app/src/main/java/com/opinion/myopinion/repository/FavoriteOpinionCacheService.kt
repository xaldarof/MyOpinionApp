package com.opinion.myopinion.repository

import com.opinion.myopinion.repository.entity.FavoriteOpinionEntity
import io.realm.RealmResults

interface FavoriteOpinionCacheService {
    fun saveOpinionToFavorites(favoriteOpinionEntity: FavoriteOpinionEntity)

    fun getFavoriteOpinions():RealmResults<FavoriteOpinionEntity>

    fun deleteFavoriteOpinion(opinionEntity: FavoriteOpinionEntity,position:Int)
}