package com.opinion.myopinion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.opinion.myopinion.R
import com.opinion.myopinion.databinding.FragmentHomeBinding
import com.opinion.myopinion.helpers.BottomSheetDialogShower

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.myOpinions.setOnClickListener {
            val myOpinionsFragment = MyOpinionsFragment()
            parentFragmentManager.beginTransaction().replace(R.id.layout,myOpinionsFragment).addToBackStack(null).commit()
        }

        binding.shareBtn.setOnClickListener {
            BottomSheetDialogShower(requireContext(), this).show()
        }

        binding.reportBtn.setOnClickListener {
            val reportFragment = ReportFragment()
           parentFragmentManager.beginTransaction().replace(R.id.layout,reportFragment).addToBackStack(null).commit()
        }
    }
}