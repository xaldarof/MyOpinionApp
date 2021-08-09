package com.example.myopinion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myopinion.databinding.FragmentSavedBinding
import com.example.myopinion.helpers.BundleSender
import com.example.myopinion.helpers.BundleSenderProvider
import com.example.myopinion.adapters.helpers.FavoriteFragmentAdapterServiceHelper
import com.example.myopinion.adapters.helpers.FavoriteOpinionAdapterServiceImpl
import com.example.myopinion.repository.FavoriteOpinionCacheDataSource
import com.example.myopinion.repository.FavoriteOpinionDataSource
import io.realm.Realm

class SavedFragment : Fragment() {

    private lateinit var binding: FragmentSavedBinding
    private val realm =  Realm.getDefaultInstance()
    private val favoriteOpinionDataSource = FavoriteOpinionDataSource(FavoriteOpinionCacheDataSource(realm))
    private lateinit var bundleSender: BundleSender

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSavedBinding.inflate(inflater, container, false)
        initClickActions()
        bundleSender = BundleSender(BundleSenderProvider())
        val list = favoriteOpinionDataSource.getFavoriteOpinions()


        val favoriteOpinionAdapterImpl = FavoriteOpinionAdapterServiceImpl(
            FavoriteFragmentAdapterServiceHelper(favoriteOpinionDataSource,
            list,bundleSender,requireActivity(),this,binding.rv)
        )
        favoriteOpinionAdapterImpl.initAdapter()


        return binding.root
    }


    private fun initClickActions(){
        binding.toolBar.backBtn.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}