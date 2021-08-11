package com.example.myopinion.viewmodel

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import io.realm.Realm

class SavedFragmentViewModelFactory(private val activity: Activity,
                                    private val fragment: Fragment,
                                    private val recyclerView: RecyclerView,
                                    private val realm: Realm) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SavedFragmentViewModel::class.java)){
            return SavedFragmentViewModel(activity,fragment,recyclerView,realm) as T
    }
        throw IllegalArgumentException("Error")
    }
}