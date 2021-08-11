package com.example.myopinion.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myopinion.databinding.FragmentReportBinding
import com.example.myopinion.models.Report
import com.example.myopinion.netReq.ReportServiceProvider
import com.example.myopinion.netReq.Reporter
import com.example.myopinion.tools.SuccessDialogShower
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class ReportFragment : Fragment() {

    private lateinit var binding: FragmentReportBinding
    private val fireStore = FirebaseFirestore.getInstance()
    private val reporter = Reporter(ReportServiceProvider(fireStore))
    private val firebaseAuth = FirebaseAuth.getInstance()

    @SuppressLint("SimpleDateFormat")
    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm")
    val date = Date()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentReportBinding.inflate(inflater, container, false)

        val currentUser = firebaseAuth.currentUser
        val currentDate = simpleDateFormat.format(date)

        binding.userNameTv.text = currentUser!!.email.toString()
        binding.problemDateTv.text = currentDate
        binding.toolbar.backBtn.setOnClickListener { parentFragmentManager.popBackStack() }




        binding.sendBtn.setOnClickListener {
            val body = binding.problemBody.text.toString()
            val title = binding.problemTitle.text.toString()
            if (body.isNotEmpty() && title.isNotEmpty()){
                reporter.report(Report(body,currentUser.email.toString(),currentDate,title))
                SuccessDialogShower().showReportMessage(requireContext(),requireActivity())
                Handler(Looper.getMainLooper()).postDelayed({parentFragmentManager.popBackStack()},2000)
            }
        }

        return binding.root
    }
}