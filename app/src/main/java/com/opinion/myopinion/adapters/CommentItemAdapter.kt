package com.opinion.myopinion.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.opinion.myopinion.R
import com.opinion.myopinion.databinding.CommentItemBinding
import com.opinion.myopinion.models.Comment

class CommentItemAdapter(var list: List<Comment>,var onItemClickListener: OnItemClickListener,private val context: Context) : RecyclerView.Adapter<CommentItemAdapter.VH>(){
    inner class VH(var commentItemBinding: CommentItemBinding): RecyclerView.ViewHolder(commentItemBinding.root){
        fun onBind(comment: Comment,position: Int){

            commentItemBinding.tvBody.text = comment.body
            commentItemBinding.tvDate.text = comment.date

            if (comment.author.isEmpty()){
                commentItemBinding.tvName.text = context.resources.getString(R.string.userAnonym)
            } else{
                commentItemBinding.tvName.text = comment.author
            }
            commentItemBinding.commentImage.text = comment.emailOfSender[0].toString()
            commentItemBinding.reportComment.setOnClickListener {
                onItemClickListener.onClick(comment,position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(CommentItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position],position)
    }

    override fun getItemCount(): Int {
        return list.size
    }
    interface OnItemClickListener{
        fun onClick(comment: Comment,position: Int)
    }
}