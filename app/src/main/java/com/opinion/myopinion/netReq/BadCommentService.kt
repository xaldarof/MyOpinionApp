package com.opinion.myopinion.netReq

import com.opinion.myopinion.models.BadComment

interface BadCommentService {

    fun sendReport(badComment: BadComment)
}