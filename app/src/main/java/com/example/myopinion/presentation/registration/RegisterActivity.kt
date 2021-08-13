package com.example.myopinion.presentation.registration

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myopinion.R
import com.example.myopinion.databinding.ActivityRegisterBinding
import com.example.myopinion.fragments.SignInFragment
import com.example.myopinion.netReq.UserRegister
import com.example.myopinion.netReq.UserRegisterRequestProvider
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.loginBtn.setOnClickListener {
            binding.apply {
                val userRegister = UserRegister(UserRegisterRequestProvider(firebaseAuth,this@RegisterActivity))
                userRegister.register(loginInputText.text.toString(),passwordInputText.text.toString())
            }
        }
        binding.signInTextBtn.setOnClickListener {
            val signInFragment = SignInFragment()
            supportFragmentManager.beginTransaction().replace(R.id.register_layout,signInFragment)
                .addToBackStack(null)
                .commit()
        }

    }
}