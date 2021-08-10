package com.example.myopinion.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myopinion.R
import com.example.myopinion.databinding.ItemBinding
import com.example.myopinion.models.Opinion

class SearchFragmentItemAdapter(
    private var list: List<Opinion>,
    private val onClickListener: OnClickListener,
    var context: Context

) :
    RecyclerView.Adapter<SearchFragmentItemAdapter.VH>() {

    inner class VH(var itemBinding: ItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        @SuppressLint("SetTextI18n")
        fun onBind(opinion: Opinion, position: Int) {
            itemBinding.tvAuthor.text = opinion.username
            itemBinding.tvType.text = opinion.type
            itemBinding.tvDescription.text = opinion.shortDescription

            when (opinion.type) {
                context.resources.getString(R.string.life) -> {
                    itemBinding.typeImageView.setImageResource(R.drawable.life)
                }
                context.resources.getString(R.string.Business) -> {
                    itemBinding.typeImageView.setImageResource(R.drawable.business)
                }
                context.resources.getString(R.string.Carreer) -> {
                    itemBinding.typeImageView.setImageResource(R.drawable.carreer)
                }
                context.resources.getString(R.string.Love) -> {
                    itemBinding.typeImageView.setImageResource(R.drawable.love)
                }
                context.resources.getString(R.string.Tech) -> {
                    itemBinding.typeImageView.setImageResource(R.drawable.tech)
                }
                context.resources.getString(R.string.Family) -> {
                    itemBinding.typeImageView.setImageResource(R.drawable.family)
                }
                context.resources.getString(R.string.Sport) -> {
                    itemBinding.typeImageView.setImageResource(R.drawable.sports)
                }
            }

            itemBinding.saveImageView.setOnClickListener {
                onClickListener.onClickSave(opinion, position)
            }
            itemBinding.writeCommentBtn.setOnClickListener {
                onClickListener.onClickComment(opinion, position)
            }
            itemBinding.layout.setOnClickListener {
                onClickListener.onClickRead(opinion.body,opinion.shortDescription)
            }

        }
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position],position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(filteredList: List<Opinion>){
        list = filteredList
        notifyDataSetChanged()
    }

    interface OnClickListener {
        fun onClickSave(opinion: Opinion, position: Int)

        fun onClickComment(opinion: Opinion, position: Int)

        fun onClickRead(body:String,shortDescription:String)
    }

}