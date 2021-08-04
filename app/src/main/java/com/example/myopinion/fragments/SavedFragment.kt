package com.example.myopinion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myopinion.databinding.FragmentSavedBinding


class SavedFragment : Fragment() {

    private lateinit var binding: FragmentSavedBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSavedBinding.inflate(inflater, container, false)



        binding.button.setOnClickListener {
            fragmentManager?.popBackStack()
        }
        return binding.root
    }
}