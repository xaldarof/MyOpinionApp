package com.opinion.myopinion.viewmodel

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class SignInViewModelFactory(private val activity: Activity) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignInFragmentViewModel::class.java)){
            return SignInFragmentViewModel(activity) as T
        }

        throw IllegalArgumentException("Error ")
    }
}