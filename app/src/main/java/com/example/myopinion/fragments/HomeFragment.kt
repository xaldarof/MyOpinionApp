package com.example.myopinion.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myopinion.R

import com.example.myopinion.databinding.FragmentHomeBinding
import com.example.myopinion.helpers.BottomSheetDialogShower

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
    }
}