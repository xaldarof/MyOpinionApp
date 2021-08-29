package com.opinion.myopinion.helpers

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.opinion.myopinion.adapters.SearchFragmentItemAdapter
import com.opinion.myopinion.models.Opinion
import com.opinion.myopinion.presentation.EditingActivity
import com.opinion.myopinion.presentation.ReadingActivity
import com.opinion.myopinion.repository.FavoriteOpinionCacheDataSource
import com.opinion.myopinion.repository.FavoriteOpinionDataSource
import com.opinion.myopinion.repository.OpinionToFavoriteOpinionEntity
import com.opinion.myopinion.repository.entity.FavoriteOpinionEntity
import io.realm.Realm

class SearchFragmentAdapterProvider(private val list: List<Opinion>, private val context: Context,
                                    private val fragment: Fragment, private val recyclerView: RecyclerView,
                                    private val bundleSender: BundleSender)
    : SearchFragmentAdapterService {

    private lateinit var searchFragmentItemAdapter: SearchFragmentItemAdapter
    private val realm = Realm.getDefaultInstance()
    private val favoriteOpinionDataSource = FavoriteOpinionDataSource(FavoriteOpinionCacheDataSource(realm))


    override fun initAdapter() {
        searchFragmentItemAdapter = SearchFragmentItemAdapter(list, object : SearchFragmentItemAdapter.OnClickListener {

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
                val intent = Intent(context, EditingActivity::class.java)
                intent.putExtra("opinion",opinion)
                context.startActivity(intent)
            }

        }, context,CommentCounter.Base())
        recyclerView.adapter = searchFragmentItemAdapter
    }

   override fun notifyDataSetChanged(){
        searchFragmentItemAdapter.notifyDataSetChanged()
   }

    override fun updateList(filteredList: List<Opinion>) {
        searchFragmentItemAdapter.updateList(filteredList)
    }

    private fun saveThisOpinionToView(opinion: Opinion){
        val opinionToFavoriteOpinionEntity = OpinionToFavoriteOpinionEntity(
            FavoriteOpinionEntity()
        )
        val mappedOpinionEntity = opinionToFavoriteOpinionEntity.opinionToFavoriteEntity(opinion)
        favoriteOpinionDataSource.saveOpinionToFavorites(mappedOpinionEntity)
    }
}