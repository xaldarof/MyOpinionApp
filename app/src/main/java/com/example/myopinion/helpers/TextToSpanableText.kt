package com.example.myopinion.helpers

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.example.myopinion.R

class TextToSpanableText(private val context: Context){

    private lateinit var dialog:AlertDialog.Builder
    fun init(checkBox: CheckBox,appCompatButton: AppCompatButton,textView: TextView){
        val wordtoSpan: Spannable =
            SpannableString( "Принимаю условия пользовательского соглашения.")
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(p0: View) {
                dialog = AlertDialog.Builder(context)
                    .setTitle("Политика конфиденциальности")
                    .setMessage(context.resources.getString(R.string.politics_text))
                    .setPositiveButton("Принимаю",object :DialogInterface.OnClickListener{
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            checkBox.isChecked = true
                            appCompatButton.isEnabled = true
                        }
                    })
                dialog.show()
            }
        }
        wordtoSpan.setSpan(clickableSpan, 8, 17, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        textView.text = wordtoSpan
        textView.movementMethod = LinkMovementMethod.getInstance()

    }
}