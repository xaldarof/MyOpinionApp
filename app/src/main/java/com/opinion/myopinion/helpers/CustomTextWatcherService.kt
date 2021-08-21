package com.opinion.myopinion.helpers

import android.widget.EditText
import android.widget.TextView

interface CustomTextWatcherService {
    fun observeInput(editText: EditText, textView: TextView)
}