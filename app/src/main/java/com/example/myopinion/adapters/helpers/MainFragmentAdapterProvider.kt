package com.example.myopinion.adapters.helpers

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.myopinion.R
import com.example.myopinion.adapters.ItemAdapter
import com.example.myopinion.helpers.BundleSender
import com.example.myopinion.models.Opinion
import com.example.myopinion.presentation.ReadingActivity
import com.example.myopinion.repository.FavoriteOpinionCacheDataSource
import com.example.myopinion.repository.FavoriteOpinionDataSource
import com.example.myopinion.repository.OpinionToFavoriteOpinionEntity
import com.example.myopinion.repository.entity.FavoriteOpinionEntity
import io.realm.Realm

class MainFragmentAdapterProvider(private val list: List<Opinion>, private val context: Context,
                                  private val fragment: Fragment, private val recyclerView: RecyclerView,
                                  private val bundleSender: BundleSender
                                ) : MainFragmentAdapterService {

    private lateinit var itemAdapter: ItemAdapter
    private val realm = Realm.getDefaultInstance()
    private val favoriteOpinionDataSource = FavoriteOpinionDataSource(FavoriteOpinionCacheDataSource(realm))

    override fun initAdapter() {

        itemAdapter = ItemAdapter(list, object : ItemAdapter.OnClickListener {
            override fun onClickSave(opinion: Opinion, position: Int) {
                val opinionToFavoriteOpinionEntity = OpinionToFavoriteOpinionEntity(
                    FavoriteOpinionEntity()
                )
                val mappedOpinionEntity = opinionToFavoriteOpinionEntity.opinionToFavoriteEntity(opinion)
                Toast.makeText(context, context.resources.getString(R.string.added_to_favorites), Toast.LENGTH_SHORT).show()
                favoriteOpinionDataSource.saveOpinionToFavorites(mappedOpinionEntity)

            }

            override fun onClickComment(opinion: Opinion, position: Int) {
                bundleSender.sendBundle(fragment, opinion.postId)
            }

            override fun onClickRead(opinion: Opinion, position: Int) {
                val intent = Intent(context, ReadingActivity::class.java)
                intent.putExtra("opinion",opinion)
                context.startActivity(intent)
            }
        }, context)
        recyclerView.adapter = itemAdapter
    }
    override fun notifyDataSetChanged(){
        itemAdapter.notifyDataSetChanged()
    }
}