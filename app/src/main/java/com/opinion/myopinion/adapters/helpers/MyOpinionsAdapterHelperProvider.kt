package com.opinion.myopinion.adapters.helpers

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.opinion.myopinion.adapters.MyOpinionsItemAdapter
import com.opinion.myopinion.helpers.BundleSender
import com.opinion.myopinion.models.Opinion
import com.opinion.myopinion.presentation.EditingActivity
import com.opinion.myopinion.presentation.ReadingActivity
import com.opinion.myopinion.repository.OpinionEntityToOpinion
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

            override fun onClickRead(opinion: OpinionEntity, position: Int) {
                val user = FirebaseAuth.getInstance().currentUser!!.email.toString()
                val intent = Intent(fragment.requireContext(), ReadingActivity::class.java)
                intent.putExtra("opinion",
                    Opinion(opinion.title.toString(),opinion.type.toString(),opinion.username.toString(),
                    opinion.date.toString(),opinion.shortDescription.toString(),
                    opinion.exactTheme.toString(),opinion.body.toString(),opinion.postId.toString(),user))

                fragment.requireContext().startActivity(intent)
            }

            override fun onClickEdit(opinion: OpinionEntity, position: Int) {
                val intent = Intent(activity.baseContext, EditingActivity::class.java)
                intent.putExtra("opinion",OpinionEntityToOpinion().entityToOpinionMapper(opinion))
                activity.startActivity(intent)
            }

        },activity.applicationContext)
        recyclerView.adapter = adapter
    }

    override fun notifyDataSetChanged() {
        adapter.notifyDataSetChanged()
    }
}