package com.example.myopinion.netReq

import com.example.myopinion.models.BadComment

interface BadCommentService {

    fun sendReport(badComment: BadComment)
}