package com.opinion.myopinion.helpers

import android.content.Context
import android.graphics.Color
import android.widget.EditText
import android.widget.ImageView
import com.opinion.myopinion.R

class WriteCommentTextWatcher(private val context: Context) {

    fun init(editText: EditText,imageView: ImageView){
        editText.addTextChangedListener(TextWatcherHelper(object :MyTextWatcher{
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s!!.isNotEmpty()){
                    imageView.setColorFilter(Color.BLUE)
                    imageView.isEnabled = true
                }else {
                    imageView.setColorFilter(context.resources.getColor(R.color.def_color))
                    imageView.isEnabled = false
                }
            }
        }))
    }
}