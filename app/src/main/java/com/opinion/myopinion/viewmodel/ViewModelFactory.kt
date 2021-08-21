package com.opinion.myopinion.viewmodel

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val fragment: Fragment) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel() as T
            }
            modelClass.isAssignableFrom(CommentsViewModel::class.java) -> {
                CommentsViewModel(fragment) as T
            }
            modelClass.isAssignableFrom(SearchFragmentViewModel::class.java) -> {
                SearchFragmentViewModel() as T
            }
            else -> throw IllegalArgumentException("Error ")
        }

    }
}