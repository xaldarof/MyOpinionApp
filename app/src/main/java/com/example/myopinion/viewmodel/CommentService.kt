package com.example.myopinion.viewmodel

import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import com.example.myopinion.models.Comment

interface CommentService {
    fun getCommentsLiveData(): MutableLiveData<List<Comment>>

    fun sendComment(editText: EditText)
}