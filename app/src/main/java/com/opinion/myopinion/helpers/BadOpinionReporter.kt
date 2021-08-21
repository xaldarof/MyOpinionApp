package com.opinion.myopinion.helpers

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.opinion.myopinion.R
import com.opinion.myopinion.models.BadOpinionModel
import com.google.firebase.firestore.FirebaseFirestore

interface Reporter {
    fun report(postId: String, sender: String)
}

class BadOpinionReportProvider(
    private var firestore: FirebaseFirestore,
    private val context: Context
) : Reporter {
    override fun report(postId: String, sender: String) {
        firestore = FirebaseFirestore.getInstance()

        val dialog = Dialog(context)
        dialog.setContentView(R.layout.bad_opinion_report)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val buttonApply = dialog.findViewById<AppCompatButton>(R.id.applyBtn)
        val cancelBtn = dialog.findViewById<AppCompatButton>(R.id.cancel_button)
        val passwordEditText = dialog.findViewById<EditText>(R.id.editText)
        val gmail = dialog.findViewById<TextView>(R.id.gmailTv)
        gmail.text = sender

        buttonApply.setOnClickListener {
            firestore.collection("bad_opinions").add(
                BadOpinionModel(postId, passwordEditText.text.toString(), gmail.text.toString())
            )
                .addOnSuccessListener {
                    Toast.makeText(context, "Отправлено на проверку...", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
        }
        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

}

class BadOpinionReporter(private val badOpinionReportProvider: BadOpinionReportProvider) :
    Reporter {
    override fun report(postId: String, sender: String) {
        badOpinionReportProvider.report(postId, sender)
    }
}