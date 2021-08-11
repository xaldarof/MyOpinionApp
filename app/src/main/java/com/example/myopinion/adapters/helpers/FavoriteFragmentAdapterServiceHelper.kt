package com.example.myopinion.adapters.helpers

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.myopinion.R
import com.example.myopinion.adapters.FavoriteOpinionItemAdapter
import com.example.myopinion.databinding.FragmentSavedBinding
import com.example.myopinion.helpers.BundleSender
import com.example.myopinion.repository.FavoriteOpinionDataSource
import com.example.myopinion.repository.entity.FavoriteOpinionEntity
import com.example.myopinion.tools.CommentsChecker.Companion.check
import io.realm.RealmResults

class FavoriteFragmentAdapterServiceHelper (private val favoriteOpinionDataSource: FavoriteOpinionDataSource,
                                            private var list: RealmResults<FavoriteOpinionEntity>,
                                            private val bundleSender: BundleSender, private val activity: Activity,
                                            private val fragment:Fragment, private val recyclerView: RecyclerView
                                     ): FavoriteFragmentAdapterService {

    private lateinit var favOpinionsItemAdapter : FavoriteOpinionItemAdapter

    override fun initAdapter() {
        val savedFragmentBinding = FragmentSavedBinding.inflate(activity.layoutInflater)
        savedFragmentBinding.tvInfo.check(list)

        favOpinionsItemAdapter = FavoriteOpinionItemAdapter(list,object : FavoriteOpinionItemAdapter.OnClickListener {
            override fun onClickShare(opinion: FavoriteOpinionEntity, position: Int) {
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT,opinion.shortDescription)
                    type = "text/plain"
                }
                activity.startActivity(shareIntent)
            }

            override fun onClickComment(opinion: FavoriteOpinionEntity, position: Int) {
                bundleSender.sendBundle(fragment,opinion.postId.toString())
            }

            override fun onClickDelete(opinion: FavoriteOpinionEntity, position: Int) {
                favoriteOpinionDataSource.deleteFavoriteOpinion(opinion,position)
                notifyDataSetChanged(position)
                Toast.makeText(fragment.requireContext(), fragment.requireContext().resources.getString(R.string.deleted_from_favorites), Toast.LENGTH_SHORT).show()

            }
        },fragment.requireContext())
        recyclerView.adapter = favOpinionsItemAdapter

    }

    override fun notifyDataSetChanged(position:Int) {
        favOpinionsItemAdapter.notifyDataSetChanged()
    }
}