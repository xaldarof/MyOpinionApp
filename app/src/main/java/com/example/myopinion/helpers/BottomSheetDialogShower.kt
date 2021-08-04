package com.example.myopinion.helpers

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView
import com.example.myopinion.R
import com.example.myopinion.presentation.WriteOpinionActivity
import com.example.myopinion.adapters.OpinionThemeItemAdapter
import com.example.myopinion.fragments.HomeFragment
import com.example.myopinion.models.TypeOpinionItem
import com.example.myopinion.utils.KeyWords
import com.example.myopinion.utils.ListHolder
import com.google.android.material.bottomsheet.BottomSheetDialog

@SuppressLint("InflateParams")
class BottomSheetDialogShower(private val context: Context,private val homeFragment: HomeFragment) {

    private val listHolder = ListHolder(context)

    @SuppressLint("WrongViewCast")
    fun show() {
        val bottomSheetDialog = BottomSheetDialog(context, R.style.BottomSheetDialogTheme)
        val view = LayoutInflater.from(context)
            .inflate(R.layout.bottom_sheet, homeFragment.requireActivity().findViewById(R.id.container) as LinearLayout?)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv)
        val button = view.findViewById<Button>(R.id.continueBtn)
        val tv = view.findViewById<TextView>(R.id.tvTypeInfo)
        val tvType = view.findViewById<TextView>(R.id.tvSelectedType)

        val opinionThemeItemAdapter = OpinionThemeItemAdapter(
            listHolder.getTypeList(),
            object : OpinionThemeItemAdapter.OnClickListener {
                override fun onClick(typeOpinionItem: TypeOpinionItem) {
                    tvType.text = typeOpinionItem.type
                }
            })

        val layoutManager = LinearLayoutManager(context,HORIZONTAL,false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = opinionThemeItemAdapter

        tvType.setOnClickListener {
            if (tvType.text.equals(context.resources.getString(R.string.back))){
                bottomSheetDialog.dismiss()
            }
        }

        button.setOnClickListener {
            if (!tvType.text.equals(context.resources.getString(R.string.back))){
                val intent = Intent(homeFragment.context, WriteOpinionActivity::class.java)
                intent.putExtra(KeyWords.OPINION_TYPE,tvType.text.toString())
                homeFragment.startActivity(intent)
                bottomSheetDialog.dismiss()

            } else {
                tv.text = context.resources.getString(R.string.notSelectedText)
                tv.setTextColor(context.resources.getColor(R.color.red))
            }
        }

        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()

    }
}