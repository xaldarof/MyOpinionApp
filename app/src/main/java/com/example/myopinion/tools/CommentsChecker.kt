package com.example.myopinion.tools

import android.view.View
import android.widget.TextView

class CommentsChecker {
    companion object {
        fun <T> TextView.check(list:List<T>){
            if (list.isNotEmpty()) {
                this.visibility = View.INVISIBLE
            } else {
                this.visibility = View.VISIBLE
            }
        }
    }
}