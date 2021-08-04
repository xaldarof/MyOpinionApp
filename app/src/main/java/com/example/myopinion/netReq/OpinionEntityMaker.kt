package com.example.myopinion.netReq

import com.example.myopinion.models.Opinion
import com.example.myopinion.repository.entity.OpinionEntity

class OpinionEntityMaker(private val opinionEntityProvider: OpinionEntityProvider): OpinionEntityMakerService {
    override fun opinionToOpinionEntity(): OpinionEntity {
        return opinionEntityProvider.opinionToOpinionEntity()
    }
}