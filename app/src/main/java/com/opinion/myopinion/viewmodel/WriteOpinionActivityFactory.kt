package com.opinion.myopinion.viewmodel

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.opinion.myopinion.databinding.ActivityWriteOpinionBinding
import io.realm.Realm
import java.lang.IllegalArgumentException

class WriteOpinionActivityFactory(private val binding:ActivityWriteOpinionBinding,private val type:String,
                                  private val activity: Activity,private val context: Context,private val realm: Realm) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WriteOpinionViewModel::class.java)){
            return WriteOpinionViewModel(binding,type,activity,context,realm) as T
        }
        throw IllegalArgumentException("error")
    }
}