package com.opinion.myopinion.adapters.helpers

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.opinion.myopinion.adapters.MyOpinionsItemAdapter
import com.opinion.myopinion.helpers.BundleSender
import com.opinion.myopinion.repository.entity.OpinionEntity
import io.realm.RealmResults

class MyOpinionsAdapterHelperProvider(private val activity: Activity, private val fragment:Fragment,
                                      private val list: RealmResults<OpinionEntity>,
                                      private val bundleSender: BundleSender,
                                      private val recyclerView: RecyclerView
                              ) : MyAdapterService {

    private lateinit var adapter : MyOpinionsItemAdapter

    override fun initAdapter() {
        adapter = MyOpinionsItemAdapter(list,object : MyOpinionsItemAdapter.OnClickListener{
            override fun onClickShare(opinion: OpinionEntity, position: Int) {
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT,opinion.shortDescription)
                    type = "text/plain"
                }
               activity.startActivity(shareIntent)
            }

            override fun onClickComment(opinion: OpinionEntity, position: Int) {
                bundleSender.sendBundle(fragment,opinion.postId.toString())
            }

        },activity.applicationContext)
        recyclerView.adapter = adapter
    }

    override fun notifyDataSetChanged() {
        adapter.notifyDataSetChanged()
    }
}