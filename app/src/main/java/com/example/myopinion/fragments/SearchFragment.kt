package com.example.myopinion.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.myopinion.R
import com.example.myopinion.databinding.FragmentSearchBinding
import com.example.myopinion.helpers.*
import com.example.myopinion.models.Opinion
import com.example.myopinion.repository.Status
import com.example.myopinion.tools.TopSnackBarShower
import com.example.myopinion.viewmodel.SearchFragmentViewModel
import com.example.myopinion.viewmodel.ViewModelFactory
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
                Status.SUCCESS -> { list.addAll(it.data!!)
                    list.shuffle()
                    searchFragmentAdapter.notifyDataSetChanged()
                    for (i in 0 until 100){
                        val random = (0..10).random()
                        list.add(Opinion("SASASsasasas","SASASsasasas","SASASsasasas","SASASsasasas","$random","SASASsasasas","SASASsasasas","SASASsasasas"))
                    }
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