package com.example.myopinion.viewmodel

import android.app.Activity
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myopinion.netReq.UserCreator
import com.example.myopinion.netReq.UserCreatorProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.launch

class SignInFragmentViewModel(activity: Activity) : ViewModel() {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference = firebaseDatabase.getReference("users")

    private val userCreator = UserCreator(UserCreatorProvider(firebaseAuth,activity,databaseReference,firebaseDatabase))

    fun createUser(login:String,password:String,name:String,surname:String) = viewModelScope.launch {
        userCreator.createUser(login,password,name,surname)
        Log.d("dataa","$login $password")
    }
}
