package com.opinion.myopinion.service

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService

class FireBaseMessageService : FirebaseMessagingService() {
    override fun onNewToken(p0: String) {
        Log.d("message2", "[NEW TOKEN] = $p0")
    }
}