package com.example.myopinion.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myopinion.models.Notification
import com.example.myopinion.netRes.NotificationDataSource
import com.example.myopinion.netRes.NotificationsCloudDataSource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class NotificationFragmentViewModel : ViewModel() {

    private val notificationLiveData = MutableLiveData<List<Notification>>()
    private val firebaseFirestore = FirebaseFirestore.getInstance()
    private val notificationDataSource = NotificationDataSource(NotificationsCloudDataSource(firebaseFirestore))

    init {
        getNotifications()
    }

    private fun getNotifications() = viewModelScope.launch{
        notificationDataSource.getNotifications().observeForever {
            notificationLiveData.postValue(it)
        }
    }
    fun getNotificationsLiveData():MutableLiveData<List<Notification>>{
        return notificationLiveData
    }
}