package com.example.myopinion.utils

import android.content.Context
import android.graphics.Color
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.andrognito.pinlockview.IndicatorDots
import com.andrognito.pinlockview.PinLockView
import com.andrognito.pinlockview.PinLockListener
import com.example.myopinion.repository.Password


interface PinLockerCallBack {
    fun showPinLocker(pinLockView: PinLockView,indicatorDots: IndicatorDots)
}

class PinLocker(private val context: Context) : PinLockerCallBack{
    private val TAG = "pin"
    private lateinit var password: Password
    private val sharedPreferences = context.getSharedPreferences("password", AppCompatActivity.MODE_PRIVATE)

    override fun showPinLocker(pinLockView: PinLockView,indicatorDots: IndicatorDots) {
        pinLockView.attachIndicatorDots(indicatorDots)

        pinLockView.setPinLockListener(object : PinLockListener {
            override fun onComplete(pin: String) {
                Log.d(TAG, "Pin complete: $pin")
                pinLockView.resetPinLockView()
            }

            override fun onEmpty() {
                Log.d(TAG, "Pin empty")
            }

            override fun onPinChange(pinLength: Int, intermediatePin: String) {
                password = Password(sharedPreferences,context)
                Log.d("info","PASSWORD = ${password.getPassword()}")

            }
        })
    }

}