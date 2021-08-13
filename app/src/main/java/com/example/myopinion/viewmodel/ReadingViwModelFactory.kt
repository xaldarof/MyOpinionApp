package com.example.myopinion.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myopinion.models.Opinion
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class ReadingViwModelFactory(private var firebaseDatabase: FirebaseDatabase,
                             private var firebaseAuth: FirebaseAuth,
                             private val opinion: Opinion

) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReadingActivityViewModel::class.java)){
            return ReadingActivityViewModel(firebaseDatabase,firebaseAuth,opinion) as T
        }
        throw IllegalArgumentException("error")
    }
}