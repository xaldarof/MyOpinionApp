package com.example.myopinion.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myopinion.netReq.userProfile.UserProfileChanger
import com.example.myopinion.netReq.userProfile.UserProfileChangerProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileFragmentViewModel(private val bitmap: Bitmap,firebaseAuth: FirebaseAuth,databaseReference: DatabaseReference,firebaseDatabase: FirebaseDatabase)
    : ViewModel() {

    private val user = FirebaseAuth.getInstance().currentUser
    private val userProfileChanger = UserProfileChanger(UserProfileChangerProvider(user!!,firebaseDatabase,databaseReference,firebaseAuth))
    private val storageReference = FirebaseStorage.getInstance().reference
        .child("profile_photos")
        .child("${user?.uid}.jpeg")

    fun changeProfilePhoto() = viewModelScope.launch {
        CoroutineScope(Dispatchers.IO).launch {
            userProfileChanger.handlerUpload(bitmap)
            userProfileChanger.getDownloadedUrl(storageReference)
        }
    }
}
