package com.example.myopinion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myopinion.databinding.FragmentFillInformationBinding

class FillInformationFragment : Fragment() {

    private lateinit var binding: FragmentFillInformationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentFillInformationBinding.inflate(inflater,container,false)






        return binding.root
    }
}