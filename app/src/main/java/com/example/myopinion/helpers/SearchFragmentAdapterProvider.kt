package com.example.myopinion.helpers

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.myopinion.adapters.SearchFragmentItemAdapter
import com.example.myopinion.models.Opinion

class SearchFragmentAdapterProvider(private val list: List<Opinion>, private val context: Context,
                                    private val fragment: Fragment, private val recyclerView: RecyclerView,
                                    private val bundleSender: BundleSender)
    : SearchFragmentAdapterService {

    private lateinit var searchFragmentItemAdapter: SearchFragmentItemAdapter

    override fun initAdapter() {
        searchFragmentItemAdapter = SearchFragmentItemAdapter(list, object : SearchFragmentItemAdapter.OnClickListener {
            override fun onClickSave(opinion: Opinion, position: Int) {
                Toast.makeText(context, "Succesfully saved !", Toast.LENGTH_SHORT).show()
            }

            override fun onClickComment(opinion: Opinion, position: Int) {
                bundleSender.sendBundle(fragment, opinion.postId)
            }
        }, context)
        recyclerView.adapter = searchFragmentItemAdapter
    }

   override fun notifyDataSetChanged(){
        searchFragmentItemAdapter.notifyDataSetChanged()
   }

    override fun updateList(filteredList: List<Opinion>) {
        searchFragmentItemAdapter.updateList(filteredList)
    }
}