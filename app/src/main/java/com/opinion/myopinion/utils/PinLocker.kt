package com.opinion.myopinion.utils

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.andrognito.pinlockview.IndicatorDots
import com.andrognito.pinlockview.PinLockView
import com.andrognito.pinlockview.PinLockListener
import com.opinion.myopinion.repository.Password


interface PinLockerCallBack {
    fun showPinLocker(pinLockView: PinLockView,indicatorDots: IndicatorDots,layout: RelativeLayout)
}

class PinLocker(private val context: Context) : PinLockerCallBack{
    private val TAG = "pin"
    private lateinit var password: Password
    private val sharedPreferences = context.getSharedPreferences("password", AppCompatActivity.MODE_PRIVATE)

    override fun showPinLocker(pinLockView: PinLockView,indicatorDots: IndicatorDots,layout: RelativeLayout) {
        pinLockView.attachIndicatorDots(indicatorDots)

        pinLockView.setPinLockListener(object : PinLockListener {
            override fun onComplete(pin: String) {
                pinLockView.resetPinLockView()
                if (pin == password.getPassword()) {
                    layout.visibility = View.GONE

                }
            }

            override fun onEmpty() {
                Log.d(TAG, "Pin empty")
            }

            override fun onPinChange(pinLength: Int, intermediatePin: String) {
                password = Password(sharedPreferences,context)

            }
        })
    }

}