package com.opinion.myopinion.repository

import com.opinion.myopinion.models.Opinion
import com.opinion.myopinion.repository.entity.FavoriteOpinionEntity
import com.opinion.myopinion.repository.entity.OpinionEntity

interface MapperService {
    fun opinionToFavoriteEntity(opinion: Opinion): FavoriteOpinionEntity
}

interface Mapper {
    fun entityToOpinionMapper(opinion: OpinionEntity): Opinion
}

class OpinionToFavoriteOpinionEntity(private val favoriteOpinionEntity: FavoriteOpinionEntity) :
    MapperService {
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

class OpinionEntityToOpinion : Mapper {

    override fun entityToOpinionMapper(opinion: OpinionEntity): Opinion {
        return Opinion (
            opinion.title.toString(), opinion.type.toString(),
            opinion.username.toString(),
            opinion.date.toString(),
            opinion.shortDescription.toString(),
            opinion.exactTheme.toString(),
            opinion.body.toString(),
            opinion.postId.toString(),
            opinion.author.toString()
        )
    }
}