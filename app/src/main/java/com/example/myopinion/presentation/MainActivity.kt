package com.example.myopinion.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myopinion.R
import com.example.myopinion.adapters.FragmentAdapter
import com.example.myopinion.databinding.ActivityMainBinding
import com.example.myopinion.fragments.NotificationFragment
import com.example.myopinion.fragments.ProfileFragment
import com.example.myopinion.fragments.SavedFragment
import com.example.myopinion.fragments.SearchFragment
import com.example.myopinion.netReq.NotificationCounter
import com.example.myopinion.netReq.NotificationCounterProvider
import com.example.myopinion.tools.NetworkUtils
import com.example.myopinion.tools.TopSnackBarShower
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var fragmentAdapter: FragmentAdapter
    private lateinit var binding: ActivityMainBinding
    private val firebaseFirestore = FirebaseFirestore.getInstance()
    private val notificationCounter =
        NotificationCounter(NotificationCounterProvider(firebaseFirestore))
    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val currentUser = firebaseAuth.currentUser

        if (!NetworkUtils.isNetworkAvailable(this)) {
            TopSnackBarShower.show(binding.layout, this, resources.getString(R.string.noInternet))
        }

        fragmentAdapter = FragmentAdapter(supportFragmentManager, lifecycle)
        binding.pager.adapter = fragmentAdapter

        val savedFragment = SavedFragment()
        val profileFragment = ProfileFragment()
        val searchFragment = SearchFragment()
        val notificationFragment = NotificationFragment()
        val fm = supportFragmentManager

        binding.toolBarMain.saved.setOnClickListener {
            fm.beginTransaction().replace(R.id.layout, savedFragment).addToBackStack(null).commit()
        }
        binding.toolBarMain.myProfile.setOnClickListener {
            fm.beginTransaction().replace(R.id.layout, profileFragment).addToBackStack(null)
                .commit()
        }
        binding.toolBarMain.search.setOnClickListener {
            fm.beginTransaction().replace(R.id.layout, searchFragment).addToBackStack(null).commit()
        }

        binding.toolBarMain.notifications.setOnClickListener {
            fm.beginTransaction().replace(R.id.layout, notificationFragment).addToBackStack(null)
                .commit()
        }
    }
}