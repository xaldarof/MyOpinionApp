package com.example.myopinion.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.realm.Realm
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class MyOpinionsViewModelFactory(private val realm: Realm) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyOpinionsFragmentViewModel::class.java)){
            return MyOpinionsFragmentViewModel(realm) as T
        }
        throw IllegalArgumentException("error")
    }
}