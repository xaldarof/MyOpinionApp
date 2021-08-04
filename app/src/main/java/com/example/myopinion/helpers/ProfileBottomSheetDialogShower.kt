package com.example.myopinion.helpers

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import com.example.myopinion.R
import com.example.myopinion.fragments.ProfileFragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.ozcanalasalvar.library.view.datePicker.DatePicker
import java.text.SimpleDateFormat
import java.util.*


private class FormattedDate {
    companion object {
        @SuppressLint("SimpleDateFormat")
        private val date = Date()
        @SuppressLint("SimpleDateFormat")
        fun formatted(): String = SimpleDateFormat("dd/mm/yyyy").format(date)

    }
}
@SuppressLint("InflateParams")
class ProfileBottomSheetDialogShower(
    private val context: Context,
    private val profileFragment: ProfileFragment
) {

    @SuppressLint("WrongViewCast")
    fun show() {
        val bottomSheetDialog = BottomSheetDialog(context, R.style.BottomSheetDialogTheme)
        val view = LayoutInflater.from(context)
            .inflate(
                R.layout.profile_fragment_bottom_sheet,
                profileFragment.requireActivity().findViewById(
                    R.id.container
                ) as LinearLayout?
            )

        val name = view.findViewById<EditText>(R.id.nameEditText)
        val surname = view.findViewById<EditText>(R.id.surnameEditText)
        val hobby = view.findViewById<EditText>(R.id.hobbyEditText)
        val dateEditText = view.findViewById<EditText>(R.id.dateEditText)
        val birthDay = view.findViewById<DatePicker>(R.id.datepicker).date
        val date = FormattedDate.formatted()
        val formattedDate = Date(birthDay * 1000)

        dateEditText.setText(date)
        val button = view.findViewById<AppCompatButton>(R.id.buttonSave)

        button.setOnClickListener {
            Log.d(
                "info",
                "NAME = ${name.text} SURNAME = ${surname.text} HOBBY =  ${hobby.text} BIRTHDAY = $formattedDate DATE = $date"
            )
        }
        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()

    }
}