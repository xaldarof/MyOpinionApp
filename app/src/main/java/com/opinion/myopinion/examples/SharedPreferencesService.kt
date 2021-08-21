package com.opinion.myopinion.examples

import android.content.Context
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
