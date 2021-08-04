package com.example.myopinion.netRes

import androidx.lifecycle.MutableLiveData
import com.example.myopinion.models.Comment

class CommentsDataSource(private val commentsService: CommentsService) {

    fun getComments(): MutableLiveData<List<Comment>> {
       return commentsService.getComments()
    }
}