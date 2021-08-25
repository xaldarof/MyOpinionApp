package com.opinion.myopinion.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.opinion.myopinion.R
import com.opinion.myopinion.databinding.ItemBinding
import com.opinion.myopinion.models.Opinion
import com.opinion.myopinion.repository.entity.FavoriteOpinionEntity
import io.realm.Realm

class ItemAdapter(
    private val list: List<Opinion>,
    private val onClickListener: OnClickListener,
    var context: Context
) :
    RecyclerView.Adapter<ItemAdapter.VH>() {

    inner class VH(var itemBinding: ItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        @SuppressLint("SetTextI18n")
        fun onBind(opinion: Opinion, position: Int) {
            itemBinding.tvAuthor.text = opinion.username
            itemBinding.tvType.text = opinion.type
            itemBinding.tvDescription.text = opinion.shortDescription


            //TODO FIX

            // val realm = Realm.getDefaultInstance()
            // val realmList = realm.where(FavoriteOpinionEntity::class.java).findAll()
//            for (i in realmList.indices) {
//                if (opinion.postId == realmList[i]!!.postId){
//                    itemBinding.saveImageView.visibility = View.INVISIBLE
//                    Log.d("abcd","1")
//                }else {
//                    Log.d("abcd","0")
//                    itemBinding.saveImageView.visibility = View.VISIBLE
//                }
//            }

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
                onClickListener.onClickRead(opinion, position)
            }
            itemBinding.mainLayout.setOnClickListener {
                onClickListener.onClickRead2(opinion, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ItemAdapter.VH, position: Int) {
        holder.onBind(list[position], position)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnClickListener {
        fun onClickSave(opinion: Opinion, position: Int)

        fun onClickComment(opinion: Opinion, position: Int)

        fun onClickRead(opinion: Opinion, position: Int)

        fun onClickRead2(opinion: Opinion, position: Int)
    }
}