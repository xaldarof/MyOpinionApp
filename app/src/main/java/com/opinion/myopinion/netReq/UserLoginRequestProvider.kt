package com.opinion.myopinion.netReq

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.view.View
import android.widget.ProgressBar
import com.opinion.myopinion.R
import com.opinion.myopinion.presentation.MainActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class UserLoginRequestProvider(
    private val firebaseAuth: FirebaseAuth,
    private val activity: Activity
) : UserRegisterService {
    override fun login(login: String, password: String,progressBar: ProgressBar) {
        if (!login.endsWith("@gmail.com")){
            Snackbar.make(activity.findViewById(R.id.register_layout), "Неверный формат логина. Образец (example@gmail.com)", Snackbar.LENGTH_INDEFINITE).show()
        }else {
            if (login.isNotEmpty() && password.isNotEmpty()) {
                progressBar.visibility = View.VISIBLE
                firebaseAuth.signInWithEmailAndPassword(login, password).addOnCompleteListener {
                    progressBar.visibility = View.VISIBLE
                    if (it.isSuccessful) {
                        val intent = Intent(activity, MainActivity::class.java)
                        activity.startActivity(intent)
                        activity.finish()
                    }else {
                        Snackbar.make(activity.findViewById(R.id.register_layout), "Неверный логин или пароль", Snackbar.LENGTH_SHORT)
                            .setBackgroundTint(Color.RED)
                            .show()
                        progressBar.visibility = View.INVISIBLE
                    }
                }
            }
        }
    }
}



