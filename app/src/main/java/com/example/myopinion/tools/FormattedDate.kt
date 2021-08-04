package com.example.myopinion.tools

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

class FormattedDate {
   companion object {
       @SuppressLint("SimpleDateFormat")
       private val date = Date()
       @SuppressLint("SimpleDateFormat")
       fun formatted(): String = SimpleDateFormat("HH:mm").format(date)

   }
}