package com.example.myopinion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myopinion.databinding.FragmentSavedBinding
import com.example.myopinion.tools.CommentsChecker.Companion.check
import com.example.myopinion.viewmodel.SavedFragmentViewModel
import com.example.myopinion.viewmodel.SavedFragmentViewModelFactory
import io.realm.Realm

class SavedFragment : Fragment() {

    private lateinit var binding: FragmentSavedBinding
    private val realm = Realm.getDefaultInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSavedBinding.inflate(inflater, container, false)
        initClickActions()

        val viewModel = ViewModelProvider(this,SavedFragmentViewModelFactory(requireActivity(),this,binding.rv,realm))
            .get(SavedFragmentViewModel::class.java)
        viewModel.initAdapter()

        viewModel.getDataForCheck().observe(requireActivity(),{
            binding.tvInfo.check(it)
        })

        return binding.root
    }


    private fun initClickActions(){
        binding.toolBar.backBtn.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

    }
}