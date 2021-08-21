package com.opinion.myopinion.netRes

import androidx.lifecycle.MutableLiveData
import com.opinion.myopinion.models.Notification
import com.opinion.myopinion.utils.KeyWords.Companion.PATH_FIRE_STORE
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.collections.ArrayList

class NotificationsCloudDataSource(private var firestore: FirebaseFirestore) : NotificationsService {

    private val notificationLiveData =  MutableLiveData<List<Notification>>()
    private val notificationList = ArrayList<Notification>()

    override fun getNotifications(): MutableLiveData<List<Notification>>{
        firestore.collection(PATH_FIRE_STORE).get()
            .addOnCompleteListener {
                notificationList.clear()
                if (it.isSuccessful){
                    val notificationData = it.result
                    notificationData.forEach { result ->
                        val note = result.toObject(Notification::class.java)
                        //Log.d("tag", "$note")
                        notificationList.add(note)
                    }
                    notificationLiveData.value = notificationList
                }
            }
        return notificationLiveData
    }
}




//        firestore.collection(PATH_FIRE_STORE)
//            .add(notification)
//            .addOnSuccessListener {
//                Log.d("tag", "success")
//            }
//            .addOnFailureListener {
//                Log.d("tag", "${it.message}")
//            }