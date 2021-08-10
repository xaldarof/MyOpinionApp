package com.example.myopinion.helpers

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import com.example.myopinion.R
import com.example.myopinion.fragments.ProfileFragment
import com.example.myopinion.models.UserModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.ozcanalasalvar.library.view.datePicker.DatePicker
import java.text.SimpleDateFormat
import java.util.*


private class FormattedDate {
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
    private val profileFragment: ProfileFragment
) : BottomSheetHelpers{

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var firebaseDatabase: FirebaseDatabase

    @SuppressLint("WrongViewCast")
    override fun show() {
        val bottomSheetDialog = BottomSheetDialog(context, R.style.BottomSheetDialogTheme)
        val view = LayoutInflater.from(context)
            .inflate(R.layout.profile_fragment_bottom_sheet, profileFragment.requireActivity().findViewById(R.id.container) as LinearLayout?)

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase  = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.getReference("users")

        val name = view.findViewById<EditText>(R.id.nameEditText)
        val surname = view.findViewById<EditText>(R.id.surnameEditText)
        val hobby = view.findViewById<EditText>(R.id.hobbyEditText)
        val dateEditText = view.findViewById<EditText>(R.id.dateEditText)
        val birthDayPicker = view.findViewById<DatePicker>(R.id.datepicker)
        val dateOfRegister = FormattedDate.formatted()
        var birthDay = ""

        birthDayPicker.setDataSelectListener { date, day, month, year ->
            birthDay = "$day/$month/$year"
        }

        dateEditText.setText(dateOfRegister)
        dateEditText.isEnabled = false
        val button = view.findViewById<AppCompatButton>(R.id.buttonSave)

        button.setOnClickListener {
            databaseReference.addValueEventListener(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val imageValue = snapshot.child(firebaseAuth.currentUser!!.uid).child("profileImage").value
                    val user = UserModel(name.text.toString(),surname.text.toString(),hobby.text.toString(),birthDay,dateOfRegister,imageValue.toString(),firebaseAuth.currentUser!!.uid)
                    saveUserToFireBase(user,firebaseAuth.currentUser!!.uid)
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })

        }
        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()

    }
    private fun saveUserToFireBase(userModel: UserModel, uid:String){
        databaseReference.child(uid).setValue(userModel)
    }
}