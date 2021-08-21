package com.opinion.myopinion.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.opinion.myopinion.R
import com.opinion.myopinion.databinding.FragmentSearchBinding
import com.opinion.myopinion.helpers.*
import com.opinion.myopinion.models.Opinion
import com.opinion.myopinion.repository.Status
import com.opinion.myopinion.tools.TopSnackBarShower
import com.opinion.myopinion.viewmodel.SearchFragmentViewModel
import com.opinion.myopinion.viewmodel.ViewModelFactory
import kotlin.collections.ArrayList

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val list = ArrayList<Opinion>()
    private lateinit var searchFragmentAdapter: SearchAdapter
    private val bundleSender = BundleSender(BundleSenderProvider())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentSearchBinding.inflate(inflater,container,false)

        val viewModel = ViewModelProvider(this,ViewModelFactory(this))[SearchFragmentViewModel::class.java]
        searchFragmentAdapter = SearchAdapter(SearchFragmentAdapterProvider(list,requireContext(),this,binding.rv,bundleSender))
        searchFragmentAdapter.initAdapter()

        viewModel.getLiveData().observe(requireActivity(),{
            list.clear()
            when (it.status) {
                Status.SUCCESS -> {
                    list.addAll(it.data!!)
                    list.shuffle()
                    searchFragmentAdapter.notifyDataSetChanged()
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
        binding.toolBar.clearText.setOnClickListener {
            binding.toolBar.searchEditText.setText("")
        }
        binding.toolBar.backBtn.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        binding.toolBar.searchEditText.addTextChangedListener(TextWatcherHelper(object : MyTextWatcher{
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val textFilter = TextFilter(TextFilterProvider(list,searchFragmentAdapter))
                textFilter.filter(s.toString())
                if (binding.toolBar.searchEditText.text.toString().isNotEmpty()) binding.toolBar.clearText.visibility = View.VISIBLE
                else binding.toolBar.clearText.visibility = View.INVISIBLE
            }

        }))
        return binding.root
    }
}