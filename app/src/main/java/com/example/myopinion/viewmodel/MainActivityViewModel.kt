package com.example.myopinion.viewmodel

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myopinion.R
import com.example.myopinion.tools.NetworkUtils
import com.example.myopinion.tools.TopSnackBarShower
import com.google.android.datatransport.cct.internal.NetworkConnectionInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivityViewModel: ViewModel() {
    private val liveData = MutableLiveData<Boolean>()

   suspend fun networkObserver(context: Context):MutableLiveData<Boolean>  {
       CoroutineScope(Dispatchers.Default).launch {
           while (true){
               delay(5000)
               if (!NetworkUtils.isNetworkAvailable(context)) {
                   liveData.postValue(false)
               }else {
                   liveData.postValue(true)
               }
           }
       }
       return liveData
    }
}