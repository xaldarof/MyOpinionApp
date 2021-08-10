package com.example.myopinion.helpers

import android.content.Context
import android.view.View
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import com.example.myopinion.R

class PopupMenuShower(private val context: Context) {
    fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(context, view)
        popupMenu.inflate(R.menu.menu)
        popupMenu.show()
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.copy -> {
                    val textView = view.findViewById<TextView>(R.id.tvBody)
                    Toast.makeText(context, context.getString(R.string.copied), Toast.LENGTH_SHORT).show()
                }
                R.id.report -> Toast.makeText(context, context.getString(R.string.sentForAnalyze), Toast.LENGTH_SHORT).show()
            }
            true
        }
    }
}