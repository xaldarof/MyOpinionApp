package com.example.myopinion.presentation.registration

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.myopinion.R
import com.example.myopinion.databinding.ActivityRegisterBinding
import com.example.myopinion.fragments.SignInFragment
import com.example.myopinion.netReq.UserLogin
import com.example.myopinion.netReq.UserLoginRequestProvider
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        firebaseAuth = FirebaseAuth.getInstance()

        binding.loginBtn.setOnClickListener {
            binding.apply {
                if (loginInputText.text.toString().isNotEmpty() && passwordInputText.text.toString().isNotEmpty()) {
                    val userRegister = UserLogin(UserLoginRequestProvider(firebaseAuth, this@RegisterActivity))
                    userRegister.login(loginInputText.text.toString(), passwordInputText.text.toString())
                }
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