package com.opinion.myopinion.helpers

import android.app.Activity
import android.content.Intent

class TextShareProvider(private val activity: Activity) : TextCopyService {

    override fun shareThisText(text:String) {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT,text)
            type = "text/plain"
        }
        activity.startActivity(shareIntent)
    }
}