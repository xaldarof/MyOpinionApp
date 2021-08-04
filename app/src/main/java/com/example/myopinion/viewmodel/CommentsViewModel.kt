package com.example.myopinion.viewmodel

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myopinion.models.Comment
import com.example.myopinion.netRes.CommentsDataSource
import com.example.myopinion.netRes.CommentsCloudDataSource
import kotlinx.coroutines.launch

class CommentsViewModel(private val fragment: Fragment): ViewModel() {

    private val commentsLiveData = MutableLiveData<List<Comment>>()
    private val commentsCacheDataSource = CommentsDataSource(CommentsCloudDataSource(fragment))

    init {
        getComments()
    }

    private fun getComments() = viewModelScope.launch{
        commentsCacheDataSource.getComments().observeForever {
           commentsLiveData.postValue(it)
       }
    }
    fun getCommentsLiveData():MutableLiveData<List<Comment>> {
        return commentsLiveData
    }
}