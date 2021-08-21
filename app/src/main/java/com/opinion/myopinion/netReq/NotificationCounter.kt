package com.opinion.myopinion.netReq

import androidx.lifecycle.MutableLiveData
import com.opinion.myopinion.models.Notification

class NotificationCounter (private val notificationCounterProvider: NotificationCounterProvider): NotificationCounterService {


    override fun getCount(): MutableLiveData<ArrayList<Notification>> {
       return notificationCounterProvider.getCount()
    }
}