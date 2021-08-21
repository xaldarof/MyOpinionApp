package com.opinion.myopinion.tools

import android.view.View
import android.widget.ProgressBar

class ProgressChecker {
    companion object {
        fun<T> ProgressBar.check(list: List<T>){
            if (list.isNotEmpty()) {
                this.visibility = View.INVISIBLE
            } else {
                this.visibility = View.VISIBLE
            }
        }
    }
}