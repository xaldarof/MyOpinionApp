package com.opinion.myopinion.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.opinion.myopinion.R
import com.opinion.myopinion.adapters.helpers.MainFragmentAdapterHelper
import com.opinion.myopinion.adapters.helpers.MainFragmentAdapterProvider
import com.opinion.myopinion.databinding.FragmentMainBinding
import com.opinion.myopinion.helpers.BundleSender
import com.opinion.myopinion.helpers.BundleSenderProvider
import com.opinion.myopinion.models.Opinion
import com.opinion.myopinion.repository.Status
import com.opinion.myopinion.tools.TopSnackBarShower
import com.opinion.myopinion.viewmodel.MainViewModel
import com.opinion.myopinion.viewmodel.ViewModelFactory

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var list: ArrayList<Opinion>
    private val bundleSender = BundleSender(BundleSenderProvider())

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentMainBinding.inflate(inflater, container, false)
        list = ArrayList()

        val mainViewModel = ViewModelProvider(this, ViewModelFactory(this))[MainViewModel::class.java]

        val mainFragmentAdapterHelper = MainFragmentAdapterHelper(MainFragmentAdapterProvider(list,requireContext(),this@MainFragment,binding.rv,bundleSender))
        mainFragmentAdapterHelper.initAdapter()

        mainViewModel.getLiveData().observe(requireActivity(), {
            list.clear()
            when (it.status) {
                Status.SUCCESS -> { list.addAll(it.data!!)
                    list.shuffle()
                    mainFragmentAdapterHelper.notifyDataSetChanged()
                    binding.shimmerLayout.stopShimmer()
                    binding.shimmerLayout.visibility = View.GONE
                }
                Status.ERROR -> {
                    TopSnackBarShower.show(
                        requireView(),
                        requireActivity(),
                        resources.getString(R.string.fireBaseError)
                    )
                }
            }
        })

        return binding.root
    }
}