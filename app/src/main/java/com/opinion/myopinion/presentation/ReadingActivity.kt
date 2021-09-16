package com.opinion.myopinion.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.opinion.myopinion.databinding.ActivityReadingBinding
import com.opinion.myopinion.helpers.*
import com.opinion.myopinion.models.Opinion
import com.opinion.myopinion.viewmodel.ReadingActivityViewModel
import com.opinion.myopinion.viewmodel.ReadingViwModelFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView

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