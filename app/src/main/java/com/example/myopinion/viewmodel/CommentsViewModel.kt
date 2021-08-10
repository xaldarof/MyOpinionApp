package com.example.myopinion.viewmodel

import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myopinion.models.Comment
import com.example.myopinion.netReq.CommentDataRequest
import com.example.myopinion.netReq.CommentDataRequestProvider
import com.example.myopinion.netRes.CommentsDataSource
import com.example.myopinion.netRes.CommentsCloudDataSource
import kotlinx.coroutines.launch

class CommentsViewModel(private val fragment: Fragment): ViewModel(),CommentService {

    private val commentsLiveData = MutableLiveData<List<Comment>>()
    private val commentsCacheDataSource = CommentsDataSource(CommentsCloudDataSource(fragment))
    private lateinit var commentDataRequest: CommentDataRequest

    init {
        getComments()
    }

    private fun getComments() = viewModelScope.launch{
        commentsCacheDataSource.getComments().observeForever {
           commentsLiveData.postValue(it)
       }
    }
    override fun getCommentsLiveData():MutableLiveData<List<Comment>> {
        return commentsLiveData
    }

    override fun sendComment(editText: EditText){
        commentDataRequest = CommentDataRequest(CommentDataRequestProvider(fragment.requireContext(),fragment))
        commentDataRequest.sendComment(editText)
    }
}