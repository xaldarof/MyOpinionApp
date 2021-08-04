package com.example.myopinion.examples

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

interface SharedPreferencesService {
    fun saveString(key: String, value: String)

    fun getString(key: String, defValue: String?):String?
}

class SharedPreferencesWrapper(private val sharedPreferences: SharedPreferences) : SharedPreferencesService {
    override fun saveString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    override fun getString(key: String, defValue: String?): String? {
        return sharedPreferences.getString(key,defValue)
    }


}

class NameCacheDataSource(private val sharedPreferencesWrapper: SharedPreferencesWrapper) : SharedPreferencesService {
    override fun saveString(key: String, value: String) {
        sharedPreferencesWrapper.saveString(key, value)
    }

    override fun getString(key: String, defValue: String?): String? {
        return sharedPreferencesWrapper.getString(key,defValue)
    }

}

fun main(context: Context) {
    val sharedPreferences = context.getSharedPreferences("Username",MODE_PRIVATE)
    val nameDataCacheSource = NameCacheDataSource(SharedPreferencesWrapper(sharedPreferences))
    nameDataCacheSource.saveString("name", "Easy Code")
    nameDataCacheSource.getString("key","Empty")
}
