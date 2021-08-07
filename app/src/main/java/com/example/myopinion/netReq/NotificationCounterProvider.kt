package com.example.myopinion.netReq

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.myopinion.models.Notification
import com.example.myopinion.utils.KeyWords.Companion.PATH_FIRE_STORE
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
                    Log.d("aaaa","PROVIDER= ${list.size.toString()}")
                }

            }
            liveData.value = list
        }
        return liveData
    }
}
