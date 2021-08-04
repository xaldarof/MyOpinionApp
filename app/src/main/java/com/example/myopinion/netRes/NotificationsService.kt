package com.example.myopinion.netRes

import androidx.lifecycle.MutableLiveData
import com.example.myopinion.models.Notification

interface NotificationsService {

    fun getNotifications():MutableLiveData<List<Notification>>
}