package com.example.myopinion.examples

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

interface SharedPreferencesService<T> {

    fun save(data:T)

    class BaseSharedPreferencess<T>(private val sharedPreferences: SharedPreferences) : SharedPreferencesService<T> {
        override fun save(data: T) {
        }
    }
}

fun main(context: Context) {
    
}
