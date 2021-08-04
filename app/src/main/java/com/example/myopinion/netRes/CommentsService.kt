package com.example.myopinion.netRes

import androidx.lifecycle.MutableLiveData
import com.example.myopinion.models.Comment

interface CommentsService {
    fun getComments(): MutableLiveData<List<Comment>>
}