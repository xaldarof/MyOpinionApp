package com.example.myopinion.netReq

import androidx.lifecycle.MutableLiveData
import com.example.myopinion.models.Notification

interface NotificationCounterService {
    fun getCount(): MutableLiveData<ArrayList<Notification>>
}