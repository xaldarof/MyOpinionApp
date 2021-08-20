package com.example.myopinion.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.example.myopinion.databinding.ActivityReadingBinding
import com.example.myopinion.helpers.*
import com.example.myopinion.models.BadComment
import com.example.myopinion.models.BadOpinionModel
import com.example.myopinion.models.Opinion
import com.example.myopinion.viewmodel.ReadingActivityViewModel
import com.example.myopinion.viewmodel.ReadingViwModelFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class ReadingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReadingBinding
    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val firebaseAuth = FirebaseAuth.getInstance()

    private val textShare = TextShare(TextShareProvider(this))
    private val shareTextFormatter = ShareTextFormatter()
    private val textCopier = TextCopier(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = intent.getSerializableExtra("opinion") as Opinion
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        binding.toolBar.activityTitle.text = intent.title
        binding.bodyTv.text = intent.body
        val viewModel =
            ViewModelProvider(this, ReadingViwModelFactory(firebaseDatabase, firebaseAuth, intent))
                .get(ReadingActivityViewModel::class.java)

        viewModel.getViewCountLiveData().observe(this, {
            binding.toolBar.viewCounter.text = it.size.toString()
        })

        binding.reportBtn.setOnClickListener {
            report(intent.postId)
        }
        binding.toolBar.backBtn.setOnClickListener {
            finish()
        }

        binding.shareBtn.setOnClickListener {
            shareText(intent.date)
        }
        binding.copyBtn.setOnClickListener {
            copyTextBody()
        }
    }

    private fun report(postId:String) {
        val fireStore = FirebaseFirestore.getInstance()
        val badOpinion = BadOpinionReporter(BadOpinionReportProvider(fireStore,this))
        badOpinion.report(postId,firebaseAuth.currentUser!!.email.toString())
    }

    private fun copyTextBody() {
        textCopier.copyThisText(binding.bodyTv.text.toString())
    }

    private fun shareText(date: String) {
        textShare.shareThisText(
            shareTextFormatter.formatForShare(
                binding.toolBar.activityTitle.text.toString(),
                binding.bodyTv.text.toString(),
                date
            )
        )
    }
}