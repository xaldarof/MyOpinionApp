package com.opinion.myopinion.helpers

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatButton
import com.opinion.myopinion.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.opinion.myopinion.presentation.ProfileActivity
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class FormattedDate {
    companion object {
        @SuppressLint("SimpleDateFormat")
        private val date = Date()
        @SuppressLint("SimpleDateFormat")
        fun formatted(): String = SimpleDateFormat("dd/MM/yyyy").format(date)

    }
}
@SuppressLint("InflateParams")
class ProfileBottomSheetDialogShower(
    private val context: Context,
    private val profileActivity: ProfileActivity
) : BottomSheetHelpers{

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var firebaseDatabase: FirebaseDatabase
    private val dateFormat = SimpleDateFormat("dd/MM/yyyy").format(Date())

    @SuppressLint("WrongViewCast")
    override fun show() {
        val bottomSheetDialog = BottomSheetDialog(context, R.style.BottomSheetDialogTheme)
        val view = LayoutInflater.from(context)
            .inflate(
                R.layout.profile_fragment_bottom_sheet,
                profileActivity.findViewById(R.id.container) as LinearLayout?)

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.getReference("users")

        val name = view.findViewById<EditText>(R.id.nameEditText)
        val surname = view.findViewById<EditText>(R.id.surnameEditText)
        val hobby = view.findViewById<EditText>(R.id.hobbyEditText)
        val button = view.findViewById<AppCompatButton>(R.id.buttonSave)

        button.setOnClickListener {
            val user = HashMap<String, Any?>()
            user["dateOfRegister"] = dateFormat
            user["hobby"] = hobby.text.toString()
            user["name"] = name.text.toString()
            user["surname"] = surname.text.toString()
            user["uid"] = firebaseAuth.currentUser!!.uid

            if (name.text.toString().isNotEmpty() && surname.text.isNotEmpty()) {
                databaseReference.child(firebaseAuth.currentUser!!.uid).updateChildren(user)

                bottomSheetDialog.dismiss()
            }else {
                name.setHintTextColor(Color.RED)
                surname.setHintTextColor(Color.RED)

            }
        }
        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }
}