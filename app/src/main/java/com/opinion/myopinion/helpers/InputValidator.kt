package com.opinion.myopinion.helpers

import com.opinion.myopinion.databinding.ActivityWriteOpinionBinding
import com.opinion.myopinion.utils.KeyNums.Companion.MIN_BODY
import com.opinion.myopinion.utils.KeyNums.Companion.MIN_DESCRIPTION
import com.opinion.myopinion.utils.KeyNums.Companion.MIN_EXACT_THEME

class InputValidator(private val writeOpinionBinding: ActivityWriteOpinionBinding,private val type:String) {

    fun isValidUserInput(): Boolean {
        return writeOpinionBinding.titleEditText.text.toString().isNotEmpty() && type.isNotEmpty() &&
                writeOpinionBinding.authorNameEditText.text.toString().trim().isNotEmpty() &&
                writeOpinionBinding.shortDescriptionEditText.text.toString().trim().length > MIN_DESCRIPTION &&
                writeOpinionBinding.exactThemeEditText.text.toString().trim().length > MIN_EXACT_THEME &&
                writeOpinionBinding.bodyEditText.text.toString().trim().length > MIN_BODY
    }
}
