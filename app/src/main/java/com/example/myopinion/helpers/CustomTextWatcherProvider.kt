package com.example.myopinion.helpers

import android.content.Context
import android.os.Build
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.myopinion.R
import com.example.myopinion.tools.CustomTextWatcher
import com.example.myopinion.tools.MyTextWatcher
import com.example.myopinion.utils.KeyNums
import com.example.myopinion.utils.KeyWords

class CustomTextWatcherProvider(private val context: Context) : CustomTextWatcherService {
    override fun observeInput(editText: EditText, textView: TextView) {
        editText.addTextChangedListener(CustomTextWatcher(object : MyTextWatcher {
            @RequiresApi(Build.VERSION_CODES.M)
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                textView.text = editText.text.length.toString().plus(KeyWords.MAX_LENGHT)

                if (editText.text.length > KeyNums.MAX_LENGTH) {
                    textView.setTextColor(context.getColor(R.color.red))
                }
            }
        }))
    }
}


