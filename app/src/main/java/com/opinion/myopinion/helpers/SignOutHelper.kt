package com.opinion.myopinion.helpers

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import com.opinion.myopinion.presentation.registration.RegisterActivity
import com.google.firebase.auth.FirebaseAuth

interface SignOut {

    fun signOut()

    class SignOutHelper(private var firebaseAuth: FirebaseAuth, private val context: Context,private val activity: Activity) : SignOut{
        override fun signOut() {
          val alertDialog = AlertDialog.Builder(context)
              .setTitle("Внимание")
              .setMessage("Вы действительно хотите выйти ?")
              .setPositiveButton("Да",object : DialogInterface.OnClickListener{
                  override fun onClick(p0: DialogInterface?, p1: Int) {
                      firebaseAuth = FirebaseAuth.getInstance()
                      firebaseAuth.signOut()
                      val intent = Intent(context,RegisterActivity::class.java)
                      context.startActivity(intent)
                      activity.finish()

                  }
              })
              .setNegativeButton("Отмена",object : DialogInterface.OnClickListener{
                  override fun onClick(p0: DialogInterface?, p1: Int) {
                      //DO NOTHING
                  }
              })
            alertDialog.show()
        }
    }
}