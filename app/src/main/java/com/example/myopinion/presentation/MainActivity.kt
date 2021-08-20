package com.example.myopinion.presentation

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.example.myopinion.R
import com.example.myopinion.adapters.FragmentAdapter
import com.example.myopinion.databinding.ActivityMainBinding
import com.example.myopinion.fragments.*
import com.example.myopinion.netReq.NotificationCounter
import com.example.myopinion.netReq.NotificationCounterProvider
import com.example.myopinion.repository.Password
import com.example.myopinion.repository.PasswordChecker
import com.example.myopinion.tools.NetworkUtils
import com.example.myopinion.tools.TopSnackBarShower
import com.example.myopinion.utils.PinLocker
import com.example.myopinion.viewmodel.MainActivityViewModel
import com.example.myopinion.viewmodel.MainActivityViewModelFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var fragmentAdapter: FragmentAdapter
    private lateinit var binding: ActivityMainBinding
    private val firebaseFirestore = FirebaseFirestore.getInstance()
    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val currentUser = firebaseAuth.currentUser
        val passwordChecker = PasswordChecker(this,binding)
        passwordChecker.check()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        fragmentAdapter = FragmentAdapter(supportFragmentManager, lifecycle)
        binding.pager.adapter = fragmentAdapter

        val viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.networkObserver(this@MainActivity).observe(this@MainActivity, {
                if (it==false){
                    TopSnackBarShower.show(binding.layout,this@MainActivity, resources.getString(R.string.noInternet))
                }
            })
        }
        val savedFragment = SavedFragment()
        val profileFragment = ProfileFragment()
        val searchFragment = SearchFragment()
        val notificationFragment = NotificationFragment()
        val fm = supportFragmentManager

        binding.toolBarMain.saved.setOnClickListener {
            fm.beginTransaction().replace(R.id.layout, savedFragment).addToBackStack(null)
                .commit()
        }
        binding.toolBarMain.myProfile.setOnClickListener {
            fm.beginTransaction().replace(R.id.layout, profileFragment).addToBackStack(null)
                .commit()
        }
        binding.toolBarMain.search.setOnClickListener {
            fm.beginTransaction().replace(R.id.layout, searchFragment).addToBackStack(null)
                .commit()
        }

        binding.toolBarMain.notifications.setOnClickListener {
            fm.beginTransaction().replace(R.id.layout, notificationFragment).addToBackStack(null)
                .commit()
        }
    }
 }