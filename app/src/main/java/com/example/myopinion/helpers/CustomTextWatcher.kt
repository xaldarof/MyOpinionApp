package com.example.myopinion.helpers

import android.widget.EditText
import android.widget.TextView


class CustomTextWatcher(private val customTextWatcherService: CustomTextWatcherService){

    fun observeInput(editText: EditText, textView: TextView){
        customTextWatcherService.observeInput(editText,textView)
    }
}