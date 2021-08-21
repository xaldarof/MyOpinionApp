package com.opinion.myopinion.viewmodel

import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import com.opinion.myopinion.models.Comment

interface CommentService {
    fun getCommentsLiveData(): MutableLiveData<List<Comment>>

    fun sendComment(editText: EditText)
}