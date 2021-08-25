package com.opinion.myopinion.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.opinion.myopinion.R
import com.opinion.myopinion.databinding.MyOpinionItemBinding
import com.opinion.myopinion.repository.entity.OpinionEntity
import io.realm.RealmResults

class MyOpinionsItemAdapter(
    private val list: RealmResults<OpinionEntity>,
    private val onClickListener: OnClickListener,
    var context: Context
) :
    RecyclerView.Adapter<MyOpinionsItemAdapter.VH>() {

    inner class VH(var myOpinionItemBinding: MyOpinionItemBinding) : RecyclerView.ViewHolder(myOpinionItemBinding.root) {

        @SuppressLint("SetTextI18n")
        fun onBind(opinion: OpinionEntity, position: Int) {
            myOpinionItemBinding.tvAuthor.text = opinion.username
            myOpinionItemBinding.tvType.text = opinion.type
            myOpinionItemBinding.tvDescription.text = opinion.shortDescription

            when (opinion.type) {
                context.resources.getString(R.string.life) -> {
                    myOpinionItemBinding.typeImageView.setImageResource(R.drawable.life)
                }
                context.resources.getString(R.string.Business) -> {
                    myOpinionItemBinding.typeImageView.setImageResource(R.drawable.business)
                }
                context.resources.getString(R.string.Carreer) -> {
                    myOpinionItemBinding.typeImageView.setImageResource(R.drawable.carreer)
                }
                context.resources.getString(R.string.Love) -> {
                    myOpinionItemBinding.typeImageView.setImageResource(R.drawable.love)
                }
                context.resources.getString(R.string.Tech) -> {
                    myOpinionItemBinding.typeImageView.setImageResource(R.drawable.tech)
                }
                context.resources.getString(R.string.Family) -> {
                    myOpinionItemBinding.typeImageView.setImageResource(R.drawable.family)
                }
                context.resources.getString(R.string.Sport) -> {
                    myOpinionItemBinding.typeImageView.setImageResource(R.drawable.sports)
                }
            }

            myOpinionItemBinding.shareImageView.setOnClickListener {
                onClickListener.onClickShare(opinion, position)
            }
            myOpinionItemBinding.writeCommentBtn.setOnClickListener {
                onClickListener.onClickComment(opinion,position)
            }
            myOpinionItemBinding.layout.setOnClickListener {
                onClickListener.onClickRead(opinion,position)
            }
            myOpinionItemBinding.readBtn.setOnClickListener {
                onClickListener.onClickRead(opinion,position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(MyOpinionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyOpinionsItemAdapter.VH, position: Int) {
        holder.onBind(list[position]!!, position)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnClickListener {
        fun onClickShare(opinion: OpinionEntity, position: Int)

        fun onClickComment(opinion: OpinionEntity, position: Int)

        fun onClickRead(opinion: OpinionEntity, position: Int)
    }

}