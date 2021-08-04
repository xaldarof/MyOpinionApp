package com.example.myopinion.helpers

import android.widget.EditText
import com.example.myopinion.databinding.ActivityWriteOpinionBinding
import com.example.myopinion.utils.KeyNums.Companion.MIN_BODY
import com.example.myopinion.utils.KeyNums.Companion.MIN_DESCRIPTION
import com.example.myopinion.utils.KeyNums.Companion.MIN_EXACT_THEME

class InputValidator(private val writeOpinionBinding: ActivityWriteOpinionBinding,private val type:String) {

    fun isValidUserInput(): Boolean {
        return writeOpinionBinding.titleEditText.text.toString()
            .isNotEmpty() &&
                type.isNotEmpty() &&
                writeOpinionBinding.authorNameEditText.text.toString()
            .isNotEmpty() &&
                writeOpinionBinding.shortDescriptionEditText.text.toString().length > MIN_DESCRIPTION &&
                writeOpinionBinding.exactThemeEditText.text.toString().length > MIN_EXACT_THEME &&
                writeOpinionBinding.bodyEditText.text.toString().length > MIN_BODY
    }
}
