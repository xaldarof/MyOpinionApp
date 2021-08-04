package com.example.myopinion.netReq

import com.example.myopinion.models.Opinion
import com.example.myopinion.repository.entity.OpinionEntity

class OpinionEntityProvider(private val opinion: Opinion,private var opinionEntity: OpinionEntity) : OpinionEntityMakerService {
    override fun opinionToOpinionEntity(): OpinionEntity {
        opinionEntity = OpinionEntity()

        opinionEntity.title = opinion.title
        opinionEntity.shortDescription = opinion.shortDescription
        opinionEntity.date = opinion.date
        opinionEntity.exactTheme = opinion.exactTheme
        opinionEntity.postId = opinion.postId
        opinionEntity.username = opinion.username
        opinionEntity.body = opinion.body
        opinionEntity.type = opinion.type

        return opinionEntity
    }
}