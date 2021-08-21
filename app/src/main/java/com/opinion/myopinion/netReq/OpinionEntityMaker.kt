package com.opinion.myopinion.netReq

import com.opinion.myopinion.repository.entity.OpinionEntity

class OpinionEntityMaker(private val opinionEntityProvider: OpinionEntityProvider): OpinionEntityMakerService {
    override fun opinionToOpinionEntity(): OpinionEntity {
        return opinionEntityProvider.opinionToOpinionEntity()
    }
}