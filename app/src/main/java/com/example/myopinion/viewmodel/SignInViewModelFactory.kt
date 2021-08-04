package com.example.myopinion.viewmodel

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import kotlin.math.log

@Suppress("UNCHECKED_CAST")
class SignInViewModelFactory(private val login:String,private val password:String,private val activity: Activity) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignInFragmentViewModel::class.java)){
            return SignInFragmentViewModel(login,password,activity) as T
        }

        throw IllegalArgumentException("Error ")
    }
}