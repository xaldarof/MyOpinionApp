package com.opinion.myopinion.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.opinion.myopinion.databinding.ActivityWriteOpinionBinding
import com.opinion.myopinion.helpers.CustomTextWatcher
import com.opinion.myopinion.helpers.CustomTextWatcherProvider
import com.opinion.myopinion.tools.FormattedDate
import com.opinion.myopinion.utils.KeyWords
import com.opinion.myopinion.viewmodel.WriteOpinionActivityFactory
import com.opinion.myopinion.viewmodel.WriteOpinionViewModel
import io.realm.Realm

class WriteOpinionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWriteOpinionBinding
    private val customTextWatcher = CustomTextWatcher(CustomTextWatcherProvider(this))
    private val realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteOpinionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        customTextWatcher.observeInput(binding.bodyEditText,binding.tvCounterLength)
        val intent = intent.extras
        val type = intent?.getString(KeyWords.OPINION_TYPE).toString()

        val viewModel = ViewModelProvider(this,WriteOpinionActivityFactory(binding,type,this,this,realm))
            .get(WriteOpinionViewModel::class.java)

        binding.toolBar.exitTv.setOnClickListener { finish() }
        binding.dateEditText.setText(FormattedDate.formatted())

        binding.toolBar.publishBtn.setOnClickListener {
            viewModel.sendOpinionToFireBase()
        }
    }
}
