package com.opinion.myopinion.netReq

import androidx.lifecycle.MutableLiveData
import com.opinion.myopinion.models.Notification

interface NotificationCounterService {
    fun getCount(): MutableLiveData<ArrayList<Notification>>
}