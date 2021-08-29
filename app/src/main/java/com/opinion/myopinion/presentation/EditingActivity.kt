package com.opinion.myopinion.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.opinion.myopinion.databinding.ActivityEditingBinding
import com.opinion.myopinion.models.Opinion

class EditingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditingBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val data = intent.getSerializableExtra("opinion") as Opinion
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        binding.bodyEditText.setText(data.body)
        binding.titleEditText.setText(data.title)
        binding.shortDescriptionEditText.setText(data.shortDescription)

        firebaseDatabase = FirebaseDatabase.getInstance()
        reference = firebaseDatabase.getReference("opinion")

        binding.toolbar.publishBtn.setOnClickListener {
            val body = binding.bodyEditText.text.toString()
            val title = binding.titleEditText.text.toString()
            val description = binding.shortDescriptionEditText.text.toString()
            val map = HashMap<String,Any>()
            map["body"] = body
            map["title"] = title
            map["shortDescription"] = description

            reference.child(data.postId).updateChildren(map)
            finish()
        }
        binding.toolbar.exitTv.setOnClickListener {
            finish()
        }


    }
}