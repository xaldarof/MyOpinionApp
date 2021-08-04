package com.example.myopinion.presentation.registration

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myopinion.presentation.MainActivity
import com.example.myopinion.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

class PresentationActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        val user = firebaseAuth.currentUser

        if (user != null) {
            val mainActivity = Intent(this, MainActivity::class.java)
            startActivity(mainActivity)
            finish()
        }
        else {
            val register = Intent(this, RegisterActivity::class.java)
            startActivity(register)
            finish()
        }
    }
}