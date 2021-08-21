package com.opinion.myopinion.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.opinion.myopinion.databinding.OpinionItemThemeBinding
import com.opinion.myopinion.models.TypeOpinionItem

class OpinionThemeItemAdapter(var list: List<TypeOpinionItem>,var onClickListener: OnClickListener):  RecyclerView.Adapter<OpinionThemeItemAdapter.VH>(){

    inner class VH(var opinionItemThemeBinding: OpinionItemThemeBinding):RecyclerView.ViewHolder(opinionItemThemeBinding.root){

        fun onBind(typeOpinionItem: TypeOpinionItem){
            opinionItemThemeBinding.imageType.setImageResource(typeOpinionItem.imageView)
            opinionItemThemeBinding.tvType.text = typeOpinionItem.type

            opinionItemThemeBinding.imageType.setOnClickListener {
                onClickListener.onClick(typeOpinionItem)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(OpinionItemThemeBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position])

    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnClickListener {
        fun onClick(typeOpinionItem: TypeOpinionItem)
    }
}