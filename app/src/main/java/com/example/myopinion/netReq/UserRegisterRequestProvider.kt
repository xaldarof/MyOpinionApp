package com.example.myopinion.netReq

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import com.example.myopinion.presentation.MainActivity
import com.google.firebase.auth.FirebaseAuth

class UserRegisterRequestProvider(private val firebaseAuth: FirebaseAuth,private val activity: Activity) : UserRegisterService {
    override fun register(login: String, password: String) {
        if (login.isNotEmpty() && password.isNotEmpty()) {

            firebaseAuth.signInWithEmailAndPassword(login, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    val intent = Intent(activity, MainActivity::class.java)
                    activity.startActivity(intent)
                    activity.finish()
                } else {
                    Toast.makeText(
                        activity.applicationContext,
                        "Данный аккуант не существует",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}