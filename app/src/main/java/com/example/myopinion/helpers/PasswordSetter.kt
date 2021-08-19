package com.example.myopinion.helpers

import android.content.Context
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment

class PasswordSetter(private val context: Context,private val fragment: Fragment) {
    fun init(relativeLayout: RelativeLayout) {
        relativeLayout.setOnClickListener {
            val passwordSetDialog = PasswordSetDialog(context)
            passwordSetDialog.show()
        }
    }
}