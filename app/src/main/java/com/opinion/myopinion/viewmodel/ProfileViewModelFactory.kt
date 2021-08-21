package com.opinion.myopinion.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ProfileViewModelFactory(private val bitmap: Bitmap, private val firebaseAuth: FirebaseAuth, private val databaseReference: DatabaseReference, private val firebaseDatabase: FirebaseDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileFragmentViewModel::class.java)) {
            return ProfileFragmentViewModel(bitmap,firebaseAuth,databaseReference,firebaseDatabase) as T
        }
        throw IllegalArgumentException("Error")
    }
}