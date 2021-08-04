package com.example.myopinion.helpers

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.widget.*
import com.example.myopinion.R
import com.example.myopinion.fragments.ProfileFragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.ozcanalasalvar.library.view.datePicker.DatePicker.MONTH_ON_FIRST
import com.ozcanalasalvar.library.view.popup.DatePickerPopup
import com.ozcanalasalvar.library.view.popup.DatePickerPopup.OnDateSelectListener


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

       //val tv = view.findViewById<TextView>(R.id.tvTypeInfo)
       //val tvType = view.findViewById<TextView>(R.id.tvSelectedType)


        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()

    }
}