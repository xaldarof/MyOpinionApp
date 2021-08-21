package com.opinion.myopinion.repository

import android.content.Context
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.opinion.myopinion.databinding.ActivityMainBinding
import com.opinion.myopinion.utils.PinLocker

class PasswordChecker(private val context: Context,private val binding: ActivityMainBinding) {

    private lateinit var password: Password

    fun check(){
        val sharedPreferences = context.getSharedPreferences("password", AppCompatActivity.MODE_PRIVATE)
        password = Password(sharedPreferences,context)
        if (password.getPassword().isNotEmpty()){
            val pinLocker = PinLocker(context)
            pinLocker.showPinLocker(
                binding.pinLockerLayout.pinLocker,
                binding.pinLockerLayout.indicatorDots,
                binding.pinLockerLayout.root)
        }else {
            binding.pinLockerLayout.pinLockerLayout.visibility = View.GONE
        }
    }
}