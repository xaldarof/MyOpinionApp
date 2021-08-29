package com.opinion.myopinion.adapters.helpers

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import androidx.recyclerview.widget.RecyclerView
import com.opinion.myopinion.adapters.ItemAdapter
import com.opinion.myopinion.helpers.BundleSender
import com.opinion.myopinion.helpers.CommentCounter
import com.opinion.myopinion.models.Opinion
import com.opinion.myopinion.presentation.EditingActivity
import com.opinion.myopinion.presentation.ReadingActivity
import com.opinion.myopinion.repository.FavoriteOpinionCacheDataSource
import com.opinion.myopinion.repository.FavoriteOpinionDataSource
import com.opinion.myopinion.repository.OpinionToFavoriteOpinionEntity
import com.opinion.myopinion.repository.entity.FavoriteOpinionEntity
import io.realm.Realm

class MainFragmentAdapterProvider(private val list: List<Opinion>, private val context: Context,
                                  private val fragment: Fragment, private val recyclerView: RecyclerView,
                                  private val bundleSender: BundleSender
                                ) : MainFragmentAdapterService {

    private lateinit var itemAdapter: ItemAdapter
    private val realm = Realm.getDefaultInstance()
    private val favoriteOpinionDataSource = FavoriteOpinionDataSource(FavoriteOpinionCacheDataSource(realm))

    override fun initAdapter() {
        val layoutManager = LinearLayoutManager(context,VERTICAL,false)
        recyclerView.layoutManager = layoutManager

        itemAdapter = ItemAdapter(list, object : ItemAdapter.OnClickListener {

            override fun onClickComment(opinion: Opinion, position: Int) {
                bundleSender.sendBundle(fragment, opinion.postId)
            }

            override fun onClickRead(opinion: Opinion, position: Int) {
                val intent = Intent(context, ReadingActivity::class.java)
                intent.putExtra("opinion",opinion)
                context.startActivity(intent)
                saveThisOpinionToView(opinion)
            }

            override fun onClickReadLayout(opinion: Opinion, position: Int) {
                val intent = Intent(context, ReadingActivity::class.java)
                intent.putExtra("opinion",opinion)
                context.startActivity(intent)
                saveThisOpinionToView(opinion)
            }

            override fun onClickEdit(opinion: Opinion) {
                val intent = Intent(context,EditingActivity::class.java)
                intent.putExtra("opinion",opinion)
                context.startActivity(intent)
            }
        }, context,CommentCounter.Base())
        recyclerView.adapter = itemAdapter

    }
    override fun notifyDataSetChanged(){
        itemAdapter.notifyDataSetChanged()
    }
    private fun saveThisOpinionToView(opinion: Opinion){
        val opinionToFavoriteOpinionEntity = OpinionToFavoriteOpinionEntity(
            FavoriteOpinionEntity()
        )
        val mappedOpinionEntity = opinionToFavoriteOpinionEntity.opinionToFavoriteEntity(opinion)
        favoriteOpinionDataSource.saveOpinionToFavorites(mappedOpinionEntity)
    }
}