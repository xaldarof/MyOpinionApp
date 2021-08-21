package com.opinion.myopinion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.opinion.myopinion.adapters.helpers.MyOpinionAdapterHelper
import com.opinion.myopinion.adapters.helpers.MyOpinionsAdapterHelperProvider
import com.opinion.myopinion.databinding.FragmentMyOpinionsBinding
import com.opinion.myopinion.helpers.BundleSender
import com.opinion.myopinion.helpers.BundleSenderProvider
import com.opinion.myopinion.viewmodel.MyOpinionsFragmentViewModel
import com.opinion.myopinion.viewmodel.MyOpinionsViewModelFactory
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

        binding.toolbarMyOpinions.clearBtn.setOnClickListener {
            realm.executeTransaction {
                it.deleteAll()
                myOpinionAdapterHelper.notifyDataSetChanged()
            }
        }


        return binding.root
    }
}