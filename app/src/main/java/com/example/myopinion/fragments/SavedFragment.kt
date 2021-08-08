package com.example.myopinion.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import com.example.myopinion.adapters.FavoriteOpinionItemAdapter
import com.example.myopinion.adapters.MyOpinionsItemAdapter
import com.example.myopinion.databinding.FragmentSavedBinding
import com.example.myopinion.helpers.BundleSender
import com.example.myopinion.helpers.BundleSenderProvider
import com.example.myopinion.repository.FavoriteOpinionCacheDataSource
import com.example.myopinion.repository.FavoriteOpinionDataSource
import com.example.myopinion.repository.entity.FavoriteOpinionEntity
import com.example.myopinion.repository.entity.OpinionEntity

import io.realm.Realm


class SavedFragment : Fragment() {

    private lateinit var binding: FragmentSavedBinding
    private val realm =  Realm.getDefaultInstance()
    private val favoriteOpinionDataSource = FavoriteOpinionDataSource(FavoriteOpinionCacheDataSource(realm))
    private lateinit var favOpinionsItemAdapter: FavoriteOpinionItemAdapter
    private lateinit var bundleSender: BundleSender

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSavedBinding.inflate(inflater, container, false)
        initClickActions()
        bundleSender = BundleSender(BundleSenderProvider())

        val list = favoriteOpinionDataSource.getFavoriteOpinions()
        Log.d("realm",list.toString())

        favOpinionsItemAdapter = FavoriteOpinionItemAdapter(list,object : FavoriteOpinionItemAdapter.OnClickListener {
            override fun onClickShare(opinion: FavoriteOpinionEntity, position: Int) {
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT,opinion.shortDescription)
                    type = "text/plain"
                }
                activity?.startActivity(shareIntent)
            }

            override fun onClickComment(opinion: FavoriteOpinionEntity, position: Int) {
                bundleSender.sendBundle(this@SavedFragment,opinion.postId.toString())
            }
        },requireContext())
        binding.rv.adapter = favOpinionsItemAdapter


        return binding.root
    }


    private fun initClickActions(){
        binding.toolBar.backBtn.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}