package com.example.myopinion.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate

import androidx.lifecycle.ViewModelProvider
import com.example.myopinion.databinding.ActivitySavedOpinionsBinding
import com.example.myopinion.repository.entity.OpinionEntity
import com.example.myopinion.viewmodel.SavedUiViewModel
import io.realm.Realm

/**
 * NEED DONE THIS ACTIVITY
 */

class SavedOpinionsActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySavedOpinionsBinding
    private lateinit var viewModel: SavedUiViewModel
    private val realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySavedOpinionsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        viewModel = ViewModelProvider(this).get(SavedUiViewModel::class.java)

        val opinionEntity = OpinionEntity()

        realm.executeTransaction {
            it.insertOrUpdate(opinionEntity)

        }

        val list = realm.where(OpinionEntity::class.java).findAll()
        for(i in list){
            Log.d("aaa",i.toString())
        }
    }
}