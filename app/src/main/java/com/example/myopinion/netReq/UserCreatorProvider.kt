package com.example.myopinion.netReq

import android.app.Activity
import android.content.Intent
import com.example.myopinion.presentation.MainActivity
import com.google.firebase.auth.FirebaseAuth

class UserCreatorProvider(private val firebaseAuth: FirebaseAuth,private val activity: Activity) : UserCreatorService {
    override fun createUser(login: String, password: String) {
        if (login.isNotEmpty() && password.isNotEmpty()) {
            firebaseAuth.createUserWithEmailAndPassword(login, password).addOnCompleteListener {
                if (it.isSuccessful){
                    val intent = Intent(activity, MainActivity::class.java)
                    activity.startActivity(intent)
                    activity.finish()
                }
            }

        }
    }
}
