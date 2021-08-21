package com.opinion.myopinion.netReq

import com.opinion.myopinion.repository.entity.OpinionEntity

interface OpinionEntityMakerService {
    fun opinionToOpinionEntity(): OpinionEntity
}