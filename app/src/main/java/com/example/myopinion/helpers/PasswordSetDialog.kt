package com.example.myopinion.helpers

import android.app.Dialog
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.graphics.Color
import android.graphics.drawable.ColorDrawable

import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.example.myopinion.R
import com.example.myopinion.repository.Password

interface PasswordDialog{
    fun show()
}
class PasswordSetDialog(private val context: Context) : PasswordDialog {

    override fun show() {
        val sharedPreferences = context.getSharedPreferences("password",MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val dialog = Dialog(context)
        dialog.setContentView(R.layout.set_password_layout)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));

        val buttonApply = dialog.findViewById<AppCompatButton>(R.id.applyBtn)
        val cancelBtn = dialog.findViewById<AppCompatButton>(R.id.cancel_button)
        val passwordEditText = dialog.findViewById<EditText>(R.id.editText)
        val disableBtn = dialog.findViewById<TextView>(R.id.disablePassword)

        buttonApply.visibility = View.GONE
        passwordEditText.addTextChangedListener(TextWatcherHelper(object :MyTextWatcher{
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (passwordEditText.text.toString().length>3){
                    buttonApply.visibility = View.VISIBLE
                }
            }
        }))
        buttonApply.setOnClickListener {
            val newPassword = passwordEditText.text.toString()
            val password = Password(sharedPreferences,context)
            password.save(newPassword)
            dialog.dismiss()
        }
        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }
        disableBtn.setOnClickListener {
            editor.putString("password", "")
            editor.apply()
            Toast.makeText(context, "Пароль успешно удален !", Toast.LENGTH_SHORT).show()
        }

        dialog.show()
    }

}