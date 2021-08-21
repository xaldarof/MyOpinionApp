package com.opinion.myopinion.netRes

import androidx.lifecycle.MutableLiveData
import com.opinion.myopinion.models.Notification

interface NotificationsService {

    fun getNotifications():MutableLiveData<List<Notification>>
}