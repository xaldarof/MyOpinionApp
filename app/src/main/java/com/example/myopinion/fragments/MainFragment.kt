package com.example.myopinion.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.myopinion.R
import com.example.myopinion.adapters.helpers.MainFragmentAdapterHelper
import com.example.myopinion.adapters.helpers.MainFragmentAdapterProvider
import com.example.myopinion.databinding.FragmentMainBinding
import com.example.myopinion.helpers.BundleSender
import com.example.myopinion.helpers.BundleSenderProvider
import com.example.myopinion.models.Opinion
import com.example.myopinion.repository.Status
import com.example.myopinion.tools.ProgressChecker.Companion.check
import com.example.myopinion.tools.TopSnackBarShower
import com.example.myopinion.viewmodel.MainViewModel
import com.example.myopinion.viewmodel.ViewModelFactory

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