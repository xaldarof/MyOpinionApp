package com.opinion.myopinion.helpers

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import com.opinion.myopinion.R


interface TextCopy {

    fun copyThisText(text:String)

}
class TextCopier(private val context: Activity) : TextCopy{

    override fun copyThisText(text: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("label",text)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(context, context.resources.getString(R.string.copied), Toast.LENGTH_SHORT).show()
    }

}