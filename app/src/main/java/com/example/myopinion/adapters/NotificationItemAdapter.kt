package com.example.myopinion.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myopinion.databinding.NotificationItemBinding
import com.example.myopinion.models.Notification
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

class NotificationItemAdapter(private val list: List<Notification>) : RecyclerView.Adapter<NotificationItemAdapter.VH>(){

    inner class VH(var notificationItemBinding: NotificationItemBinding):RecyclerView.ViewHolder(notificationItemBinding.root){
        fun onBind(notification: Notification){
            notificationItemBinding.tvBody.text = notification.body
            notificationItemBinding.tvDate.text = notification.date
            notificationItemBinding.tvTitle.text = notification.title
            if (notification.image.isNotEmpty()){
                Picasso.get().load(notification.image).transform(CropCircleTransformation()).into(notificationItemBinding.image)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationItemAdapter.VH {
        return VH(NotificationItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: NotificationItemAdapter.VH, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
       return list.size
    }
}