package com.opinion.myopinion.adapters.helpers

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.opinion.myopinion.R
import com.opinion.myopinion.adapters.FavoriteOpinionItemAdapter
import com.opinion.myopinion.databinding.FragmentSavedBinding
import com.opinion.myopinion.helpers.BundleSender
import com.opinion.myopinion.models.Opinion
import com.opinion.myopinion.presentation.ReadingActivity
import com.opinion.myopinion.repository.FavoriteOpinionDataSource
import com.opinion.myopinion.repository.entity.FavoriteOpinionEntity
import com.opinion.myopinion.tools.CommentsChecker.Companion.check
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

            override fun onClickRead(opinion: FavoriteOpinionEntity, position: Int) {
                val user = FirebaseAuth.getInstance().currentUser!!.email.toString()
                val intent = Intent(fragment.requireContext(), ReadingActivity::class.java)
                intent.putExtra("opinion",Opinion(opinion.title.toString(),opinion.type.toString(),opinion.username.toString(),
                    opinion.date.toString(),opinion.shortDescription.toString(),
                    opinion.exactTheme.toString(),opinion.body.toString(),opinion.postId.toString(),user))
                fragment.requireContext().startActivity(intent)

            }
        },fragment.requireContext())
        recyclerView.adapter = favOpinionsItemAdapter

    }

    override fun notifyDataSetChanged(position:Int) {
        favOpinionsItemAdapter.notifyDataSetChanged()
    }
}