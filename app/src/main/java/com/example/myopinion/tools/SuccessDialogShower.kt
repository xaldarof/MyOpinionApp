package com.example.myopinion.tools

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Looper
import cdflynn.android.library.checkview.CheckView
import com.example.myopinion.R

interface DialogShower{
    fun showSuccessMessage(context: Context, activity: Activity)
    fun showReportMessage(context: Context, activity: Activity)
}
class SuccessDialogShower : DialogShower{

   override fun showSuccessMessage(context: Context, activity: Activity) {
       val dialog = Dialog(context)
       dialog.setContentView(R.layout.success_dialog)
       val success = dialog.findViewById<CheckView>(R.id.check)
       dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
       success.check()
       dialog.show()
       android.os.Handler(Looper.getMainLooper()).postDelayed({
           dialog.dismiss()
           activity.finish() },1000)
    }

   override fun showReportMessage(context: Context,activity: Activity){
       val dialog = Dialog(context)
       dialog.setContentView(R.layout.success_report)
       dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
       dialog.show()
       android.os.Handler(Looper.getMainLooper()).postDelayed({
           dialog.dismiss()
           },1000)
    }

}