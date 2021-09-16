package com.opinion.myopinion.helpers

import android.content.Context
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment

class PasswordSetter(private val context: Context) {
    fun init(relativeLayout: RelativeLayout) {
        relativeLayout.setOnClickListener {
            val passwordSetDialog = PasswordSetDialog(context)
            passwordSetDialog.show()
        }
    }
}