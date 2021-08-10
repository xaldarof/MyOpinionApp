package com.example.myopinion.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myopinion.R
import com.example.myopinion.databinding.ActivityReadingBinding

class ReadingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReadingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadingBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}