package com.opinion.myopinion.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.opinion.myopinion.R
import com.opinion.myopinion.databinding.FavOpinionItemBinding
import com.opinion.myopinion.repository.entity.FavoriteOpinionEntity
import io.realm.RealmResults

class FavoriteOpinionItemAdapter(
    private val list: RealmResults<FavoriteOpinionEntity>,
    private val onClickListener: OnClickListener,
    var context: Context
) :
    RecyclerView.Adapter<FavoriteOpinionItemAdapter.VH>() {

    inner class VH(var favOpinionItemBinding: FavOpinionItemBinding) : RecyclerView.ViewHolder(favOpinionItemBinding.root) {

        @SuppressLint("SetTextI18n")
        fun onBind(favoriteOpinionEntity: FavoriteOpinionEntity, position: Int) {
            favOpinionItemBinding.tvAuthor.text = favoriteOpinionEntity.username
            favOpinionItemBinding.tvType.text = favoriteOpinionEntity.type
            favOpinionItemBinding.tvDescription.text = favoriteOpinionEntity.shortDescription

            when (favoriteOpinionEntity.type) {
                context.resources.getString(R.string.life) -> {
                    favOpinionItemBinding.typeImageView.setImageResource(R.drawable.life)
                }
                context.resources.getString(R.string.Business) -> {
                    favOpinionItemBinding.typeImageView.setImageResource(R.drawable.business)
                }
                context.resources.getString(R.string.Carreer) -> {
                    favOpinionItemBinding.typeImageView.setImageResource(R.drawable.carreer)
                }
                context.resources.getString(R.string.Love) -> {
                    favOpinionItemBinding.typeImageView.setImageResource(R.drawable.love)
                }
                context.resources.getString(R.string.Tech) -> {
                    favOpinionItemBinding.typeImageView.setImageResource(R.drawable.tech)
                }
                context.resources.getString(R.string.Family) -> {
                    favOpinionItemBinding.typeImageView.setImageResource(R.drawable.family)
                }
                context.resources.getString(R.string.Sport) -> {
                    favOpinionItemBinding.typeImageView.setImageResource(R.drawable.sports)
                }
            }

            favOpinionItemBinding.shareImageView.setOnClickListener {
                onClickListener.onClickShare(favoriteOpinionEntity, position)
            }
            favOpinionItemBinding.writeCommentBtn.setOnClickListener {
                onClickListener.onClickComment(favoriteOpinionEntity,position)
            }
            favOpinionItemBinding.deleteBtn.setOnClickListener {
                onClickListener.onClickDelete(favoriteOpinionEntity,position)
            }
            favOpinionItemBinding.readBtn.setOnClickListener {
                onClickListener.onClickRead(favoriteOpinionEntity,position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(FavOpinionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: FavoriteOpinionItemAdapter.VH, position: Int) {
        holder.onBind(list[position]!!, position)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnClickListener {
        fun onClickShare(opinion: FavoriteOpinionEntity, position: Int)

        fun onClickComment(opinion: FavoriteOpinionEntity, position: Int)

        fun onClickDelete(opinion: FavoriteOpinionEntity,position: Int)

        fun onClickRead(opinion:FavoriteOpinionEntity,position: Int)
    }
}