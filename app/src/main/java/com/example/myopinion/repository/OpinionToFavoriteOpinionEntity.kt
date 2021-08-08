package com.example.myopinion.repository

import com.example.myopinion.models.Opinion
import com.example.myopinion.repository.entity.FavoriteOpinionEntity

interface MapperService{
    fun opinionToFavoriteEntity(opinion:Opinion):FavoriteOpinionEntity
}
class OpinionToFavoriteOpinionEntity(private val favoriteOpinionEntity: FavoriteOpinionEntity): MapperService {
    override fun opinionToFavoriteEntity(opinion: Opinion): FavoriteOpinionEntity {
        favoriteOpinionEntity.body = opinion.body
        favoriteOpinionEntity.date = opinion.date
        favoriteOpinionEntity.exactTheme = opinion.exactTheme
        favoriteOpinionEntity.postId = opinion.postId
        favoriteOpinionEntity.shortDescription = opinion.shortDescription
        favoriteOpinionEntity.title = opinion.title
        favoriteOpinionEntity.type = opinion.type
        favoriteOpinionEntity.username = opinion.username

        return favoriteOpinionEntity
    }
}