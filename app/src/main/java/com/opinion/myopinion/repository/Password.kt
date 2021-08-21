package com.opinion.myopinion.repository

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

interface PasswordService {
    fun save(password: String)
    fun getPassword():String
}
class Password(private var sharedPreferences: SharedPreferences,private val context: Context) : PasswordService {
    @SuppressLint("CommitPrefEdits")
    override fun save(password: String) {
        sharedPreferences = context.getSharedPreferences("password",MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("password",password)
        editor.apply()
    }

    override fun getPassword(): String {
        return sharedPreferences.getString("password","").toString()
    }
}