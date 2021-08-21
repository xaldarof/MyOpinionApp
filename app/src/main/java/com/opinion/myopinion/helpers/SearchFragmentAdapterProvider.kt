package com.opinion.myopinion.helpers

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.opinion.myopinion.adapters.SearchFragmentItemAdapter
import com.opinion.myopinion.models.Opinion
import com.opinion.myopinion.presentation.ReadingActivity

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

            override fun onClickRead(body: String, shortDescription: String) {
                val intent = Intent(context,ReadingActivity::class.java)
                intent.putExtra("body",body)
                intent.putExtra("description",shortDescription)
                context.startActivity(intent)
                Log.d("11","WORK")
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