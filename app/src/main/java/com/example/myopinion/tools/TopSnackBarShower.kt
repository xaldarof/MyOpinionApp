package com.example.myopinion.tools

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.TypedValue
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import com.androidadvance.topsnackbar.TSnackbar
import com.example.myopinion.R


class TopSnackBarShower {
   companion object {
       @SuppressLint("WrongConstant")
       fun show(view: View, activity: Activity,message:String){
           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
               val window: Window = activity.window
               window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
               window.statusBarColor = Color.RED
           }
           val snackbar = TSnackbar.make(
               view,
               message,
               TSnackbar.LENGTH_LONG
           )
           snackbar.setMaxWidth(3000)
           val snackbarView = snackbar.view
           snackbar.duration = 2000
           snackbarView.setBackgroundColor(Color.RED)
           val textView = snackbarView.findViewById<View>(R.id.snackbar_text) as TextView
           textView.setTextColor(Color.WHITE)
           textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f);
           snackbar.show()
           Handler(Looper.getMainLooper()).postDelayed({
               if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                   val window: Window = activity.window
                   window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                   window.statusBarColor = view.resources.getColor(R.color.clubHouse)
               }
               TSnackbar.make(
                   view,
                   message,
                   TSnackbar.LENGTH_LONG
               )
           },2400)
       }
   }
}