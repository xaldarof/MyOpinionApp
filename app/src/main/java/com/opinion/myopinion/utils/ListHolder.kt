package com.opinion.myopinion.utils

import android.content.Context
import com.opinion.myopinion.R
import com.opinion.myopinion.models.TypeOpinionItem

class ListHolder(private val context: Context) {

    private val list = ArrayList<TypeOpinionItem>()

    fun getTypeList():ArrayList<TypeOpinionItem> {
        list.add(TypeOpinionItem(R.drawable.life,context.resources.getString(R.string.life)))
        list.add(TypeOpinionItem(R.drawable.business,context.resources.getString(R.string.Business)))
        list.add(TypeOpinionItem(R.drawable.carreer,context.resources.getString(R.string.Carreer)))
        list.add(TypeOpinionItem(R.drawable.tech,context.resources.getString(R.string.Tech)))


        list.add(TypeOpinionItem(R.drawable.family,context.resources.getString(R.string.Family)))
        list.add(TypeOpinionItem(R.drawable.love,context.resources.getString(R.string.Love)))
        list.add(TypeOpinionItem(R.drawable.astronomy,context.resources.getString(R.string.Astronomy)))
        list.add(TypeOpinionItem(R.drawable.sports,context.resources.getString(R.string.Sport)))

        return list
    }
}