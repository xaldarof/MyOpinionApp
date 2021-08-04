package com.example.myopinion.viewmodel

import android.app.Activity
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.myopinion.netReq.UserCreator
import com.example.myopinion.netReq.UserCreatorProvider
import com.google.firebase.auth.FirebaseAuth

class SignInFragmentViewModel(private val login:String, private val password:String, activity: Activity) : ViewModel() {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val userCreator = UserCreator(UserCreatorProvider(firebaseAuth,activity))

    fun createUser() {
        userCreator.createUser(login,password)
        Log.d("dataa","$login $password")
    }
}