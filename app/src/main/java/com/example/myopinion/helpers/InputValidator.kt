package com.example.myopinion.helpers

import android.widget.EditText
import com.example.myopinion.utils.KeyNums.Companion.MIN_BODY
import com.example.myopinion.utils.KeyNums.Companion.MIN_DESCRIPTION
import com.example.myopinion.utils.KeyNums.Companion.MIN_EXACT_THEME

object InputValidator {

    fun isValid(
        titleEdt: EditText,
        type: String,
        authorEdt: EditText,
        shortDescriptionEdt: EditText,
        exactThemeEdt: EditText,
        bodyEdt: EditText
    ): Boolean {
        return titleEdt.text.toString()
            .isNotEmpty() && type.isNotEmpty() && authorEdt.text.toString()
            .isNotEmpty() && shortDescriptionEdt.text.toString().length > MIN_DESCRIPTION &&
                exactThemeEdt.text.toString().length > MIN_EXACT_THEME && bodyEdt.text.toString().length > MIN_BODY
    }
}