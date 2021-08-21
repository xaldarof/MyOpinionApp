package com.opinion.myopinion.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.opinion.myopinion.adapters.NotificationItemAdapter
import com.opinion.myopinion.databinding.FragmentNotificationBinding
import com.opinion.myopinion.models.Notification
import com.opinion.myopinion.tools.ProgressChecker.Companion.check
import com.opinion.myopinion.viewmodel.NotificationFragmentViewModel
import kotlin.collections.ArrayList

class NotificationFragment : Fragment() {

    private lateinit var binding: FragmentNotificationBinding
    private lateinit var list: ArrayList<Notification>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationBinding.inflate(inflater, container, false)
        list = ArrayList()

        val notificationItemAdapter = NotificationItemAdapter(list)
        val viewModel = ViewModelProvider(this)[NotificationFragmentViewModel::class.java]
        viewModel.getNotificationsLiveData().observe(requireActivity(),{
            list.clear()
            list.addAll(0,it)
            notificationItemAdapter.notifyDataSetChanged()
            binding.progressBar.check(list)
        })
        binding.toolBar.backBtn.setOnClickListener {
            parentFragmentManager.popBackStack()
        }


        binding.rv.adapter = notificationItemAdapter

        return binding.root
    }
}