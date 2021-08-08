package com.example.myopinion.repository

import com.example.myopinion.repository.entity.FavoriteOpinionEntity
import io.realm.RealmResults

interface FavoriteOpinionCacheService {
    fun saveOpinionToFavorites(favoriteOpinionEntity: FavoriteOpinionEntity)

    fun getFavoriteOpinions():RealmResults<FavoriteOpinionEntity>
}