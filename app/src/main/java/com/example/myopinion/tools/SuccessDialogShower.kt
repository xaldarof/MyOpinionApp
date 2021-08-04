package com.example.myopinion.tools

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Looper
import cdflynn.android.library.checkview.CheckView
import com.example.myopinion.R
import java.util.logging.Handler

class SuccessDialogShower {

    fun show(context: Context,activity: Activity) {

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
}