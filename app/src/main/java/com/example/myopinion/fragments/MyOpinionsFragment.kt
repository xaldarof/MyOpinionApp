package com.example.myopinion.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.lifecycle.ViewModelProvider
import com.example.myopinion.R
import com.example.myopinion.adapters.ItemAdapter
import com.example.myopinion.adapters.MyOpinionsItemAdapter
import com.example.myopinion.databinding.FragmentMyOpinionsBinding
import com.example.myopinion.helpers.BundleSender
import com.example.myopinion.helpers.BundleSenderProvider
import com.example.myopinion.helpers.MyOpinionAdapterHelper
import com.example.myopinion.helpers.MyOpinionsAdapterHelperProvider
import com.example.myopinion.models.Opinion
import com.example.myopinion.repository.entity.OpinionEntity
import com.example.myopinion.viewmodel.MyOpinionsFragmentViewModel
import com.example.myopinion.viewmodel.MyOpinionsViewModelFactory
import io.realm.Realm

class MyOpinionsFragment : Fragment() {

    private lateinit var binding:FragmentMyOpinionsBinding
    private val bundleSender = BundleSender(BundleSenderProvider())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentMyOpinionsBinding.inflate(inflater,container,false)
        val realm = Realm.getDefaultInstance()
        val viewModel = ViewModelProvider(this,MyOpinionsViewModelFactory(realm))[MyOpinionsFragmentViewModel::class.java]

        val list = viewModel.getOpinionsFromDatabase()
        val myOpinionAdapterHelper = MyOpinionAdapterHelper(MyOpinionsAdapterHelperProvider(requireActivity(),this,list,
            bundleSender,binding.rv))
        myOpinionAdapterHelper.initAdapter()
        myOpinionAdapterHelper.notifyDataSetChanged()

        binding.toolbarMyOpinions.backBtn.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return binding.root
    }
}