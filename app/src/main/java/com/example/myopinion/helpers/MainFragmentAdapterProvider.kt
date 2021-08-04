package com.example.myopinion.helpers

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.myopinion.adapters.ItemAdapter
import com.example.myopinion.models.Opinion

class MainFragmentAdapterProvider(private val list: List<Opinion>, private val context: Context,
                                  private val fragment: Fragment, private val recyclerView: RecyclerView,
                                  private val bundleSender: BundleSender
                                ) : MainFragmentAdapterService{

    private lateinit var itemAdapter: ItemAdapter

    override fun initAdapter() {
        itemAdapter = ItemAdapter(list, object : ItemAdapter.OnClickListener {
            override fun onClickSave(opinion: Opinion, position: Int) {
                Toast.makeText(context, "Succesfully saved !", Toast.LENGTH_SHORT).show()
            }

            override fun onClickComment(opinion: Opinion, position: Int) {
                bundleSender.sendBundle(fragment, opinion.postId)
            }
        }, context)
        recyclerView.adapter = itemAdapter
    }
    override fun notifyDataSetChanged(){
        itemAdapter.notifyDataSetChanged()
    }
}