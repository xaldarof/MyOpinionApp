package com.opinion.myopinion.tools

import android.text.Editable
import android.text.TextWatcher

interface MyTextWatcher{
    fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int)
}

class CustomTextWatcher(private val myTextWatcher: MyTextWatcher) : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        myTextWatcher.onTextChanged(s, start, before,count)

    }

    override fun afterTextChanged(s: Editable?) {

    }
}