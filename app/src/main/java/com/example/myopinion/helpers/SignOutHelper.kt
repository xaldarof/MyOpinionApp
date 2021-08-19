package com.example.myopinion.helpers

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.view.View
import com.google.firebase.auth.FirebaseAuth

interface SignOut {
    class SignOutHelper(private var firebaseAuth: FirebaseAuth, private val activity: Activity) {
        fun signOut() {
          val alertDialog = AlertDialog.Builder(activity.applicationContext)
              .setTitle("Внимание")
              .setMessage("Вы действительно хотите выйти ?")
              .setPositiveButton("Да",object : DialogInterface.OnClickListener{
                  override fun onClick(p0: DialogInterface?, p1: Int) {
                      firebaseAuth = FirebaseAuth.getInstance()
                      firebaseAuth.signOut()
                      activity.finish()
                  }
              })
              .setNegativeButton("Отмена",object : DialogInterface.OnClickListener{
                  override fun onClick(p0: DialogInterface?, p1: Int) {
                      //DO NOTHING
                  }
              })
        }
    }
}