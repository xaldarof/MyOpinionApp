package com.opinion.myopinion.netRes

import androidx.lifecycle.MutableLiveData
import com.opinion.myopinion.models.Comment

class CommentsDataSource(private val commentsService: CommentsService) {

    fun getComments(): MutableLiveData<List<Comment>> {
       return commentsService.getComments()
    }
}