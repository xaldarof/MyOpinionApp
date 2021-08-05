package com.example.myopinion.viewmodel

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myopinion.databinding.ActivityWriteOpinionBinding
import com.example.myopinion.netReq.OpinionDataRequest
import com.example.myopinion.netReq.OpinionDataRequestProvider
import io.realm.Realm
import kotlinx.coroutines.launch

class WriteOpinionViewModel(binding : ActivityWriteOpinionBinding, type:String,activity: Activity,context: Context,realm: Realm): ViewModel() {

    private val opinionDataRequest = OpinionDataRequest(OpinionDataRequestProvider(binding,type,activity,context,realm))

    fun sendOpinionToFireBase() = viewModelScope.launch{
        opinionDataRequest.sendData()
    }
}