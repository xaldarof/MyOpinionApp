package com.example.myopinion.netRes

import androidx.lifecycle.MutableLiveData
import com.example.myopinion.models.Notification

class NotificationDataSource (private val notificationsCloudDataSource: NotificationsCloudDataSource): NotificationsService {
    override fun getNotifications(): MutableLiveData<List<Notification>> {
        return notificationsCloudDataSource.getNotifications()
    }
}