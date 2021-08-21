package com.opinion.myopinion.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.opinion.myopinion.tools.NetworkUtils
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