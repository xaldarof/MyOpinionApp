package com.opinion.myopinion.netReq

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.opinion.myopinion.models.Notification
import com.opinion.myopinion.utils.KeyWords.Companion.PATH_FIRE_STORE
import com.google.firebase.firestore.FirebaseFirestore

class NotificationCounterProvider(private var firestore: FirebaseFirestore) : NotificationCounterService {

    private val liveData = MutableLiveData<ArrayList<Notification>>()

    override fun getCount(): MutableLiveData<ArrayList<Notification>> {
        val list = ArrayList<Notification>()
        firestore = FirebaseFirestore.getInstance()
        firestore.collection(PATH_FIRE_STORE).get().addOnCompleteListener {
            list.clear()
            if (it.isSuccessful) {
                val notificationData = it.result
                notificationData.forEach { result ->
                    val note = result.toObject(Notification::class.java)
                    list.add(note)
                    Log.d("aaaa","PROVIDER= ${list.size}")
                }

            }
            liveData.value = list
        }
        return liveData
    }
}
