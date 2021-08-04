package com.example.myopinion.netReq

import androidx.lifecycle.MutableLiveData
import com.example.myopinion.models.Notification

class NotificationCounter (private val notificationCounterProvider: NotificationCounterProvider): NotificationCounterService {


    override fun getCount(): MutableLiveData<ArrayList<Notification>> {
       return notificationCounterProvider.getCount()
    }
}