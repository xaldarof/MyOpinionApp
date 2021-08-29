package com.opinion.myopinion.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.opinion.myopinion.R
import com.opinion.myopinion.databinding.ItemBinding
import com.opinion.myopinion.helpers.CommentCounter
import com.opinion.myopinion.models.Opinion

class SearchFragmentItemAdapter(
    private var list: List<Opinion>,
    private val onClickListener: OnClickListener,
    var context: Context,
    private val commentCounter: CommentCounter

) :
    RecyclerView.Adapter<SearchFragmentItemAdapter.VH>() {

    inner class VH(var itemBinding: ItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        @SuppressLint("SetTextI18n")
        fun onBind(opinion: Opinion, position: Int) {
            itemBinding.tvAuthor.text = opinion.username
            itemBinding.tvType.text = opinion.type
            itemBinding.tvDescription.text = opinion.shortDescription
            commentCounter.getCommentCount(opinion.postId,itemBinding.commentCount)

            if (!FirebaseAuth.getInstance().currentUser?.email.equals(opinion.author)){
                itemBinding.editBtn.visibility = View.INVISIBLE
            }else {
                itemBinding.editBtn.visibility = View.VISIBLE
            }

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

            itemBinding.writeCommentBtn.setOnClickListener {
                onClickListener.onClickComment(opinion, position)
            }
            itemBinding.layout.setOnClickListener {
                onClickListener.onClickRead(opinion, position)
            }
            itemBinding.mainLayout.setOnClickListener {
                onClickListener.onClickReadLayout(opinion, position)
            }
            itemBinding.editBtn.setOnClickListener {
                onClickListener.onClickEdit(opinion)
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

        fun onClickComment(opinion: Opinion, position: Int)

        fun onClickRead(opinion: Opinion, position: Int)

        fun onClickReadLayout(opinion: Opinion, position: Int)

        fun onClickEdit(opinion: Opinion)
    }

}