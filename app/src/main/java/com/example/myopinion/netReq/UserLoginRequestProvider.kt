package com.example.myopinion.netReq

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.widget.Toast
import com.example.myopinion.R
import com.example.myopinion.presentation.MainActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import kotlin.math.log

class UserLoginRequestProvider(
    private val firebaseAuth: FirebaseAuth,
    private val activity: Activity
) : UserRegisterService {
    override fun login(login: String, password: String) {
        if (!login.endsWith("@gmail.com")){
            Snackbar.make(activity.findViewById(R.id.register_layout), "Неверный формат логина. Образец (example@gmail.com)", Snackbar.LENGTH_INDEFINITE).show()
        }else {
            if (login.isNotEmpty() && password.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(login, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(activity, MainActivity::class.java)
                        activity.startActivity(intent)
                        activity.finish()
                    }else {
                        Snackbar.make(activity.findViewById(R.id.register_layout), "Неверный логин или пароль", Snackbar.LENGTH_SHORT)
                            .setBackgroundTint(Color.RED)
                            .show()
                    }
                }
            }
        }
    }
}



