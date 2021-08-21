package com.opinion.myopinion.netRes

import androidx.lifecycle.MutableLiveData
import com.opinion.myopinion.models.Notification

class NotificationDataSource (private val notificationsCloudDataSource: NotificationsCloudDataSource): NotificationsService {
    override fun getNotifications(): MutableLiveData<List<Notification>> {
        return notificationsCloudDataSource.getNotifications()
    }
}