package com.opinion.myopinion.netReq

import android.widget.EditText

class CommentDataRequest(private val commentDataRequestProvider: CommentDataRequestProvider) : CommentService{
    override fun sendComment(editText: EditText) {
        commentDataRequestProvider.sendComment(editText)
    }

}