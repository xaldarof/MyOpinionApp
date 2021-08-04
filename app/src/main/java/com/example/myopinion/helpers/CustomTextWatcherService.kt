package com.example.myopinion.helpers

import android.widget.EditText
import android.widget.TextView

interface CustomTextWatcherService {
    fun observeInput(editText: EditText, textView: TextView)
}