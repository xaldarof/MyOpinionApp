package com.opinion.myopinion.netRes

import androidx.lifecycle.MutableLiveData
import com.opinion.myopinion.models.Comment

interface CommentsService {
    fun getComments(): MutableLiveData<List<Comment>>
}