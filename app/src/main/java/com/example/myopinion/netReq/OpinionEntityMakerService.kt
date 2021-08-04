package com.example.myopinion.netReq

import com.example.myopinion.models.Opinion
import com.example.myopinion.repository.entity.OpinionEntity

interface OpinionEntityMakerService {
    fun opinionToOpinionEntity(): OpinionEntity
}