package com.example.myopinion.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.myopinion.databinding.FragmentProfileBinding
import com.example.myopinion.netReq.userProfile.UserProfileCheckProvider
import com.example.myopinion.netReq.userProfile.UserProfileChecker
import com.example.myopinion.presentation.registration.RegisterActivity
import com.example.myopinion.utils.KeyNums
import com.example.myopinion.viewmodel.ProfileFragmentViewModel
import com.example.myopinion.viewmodel.ProfileViewModelFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private var user: FirebaseUser? = null
    private lateinit var userProfileChecker: UserProfileChecker

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        firebaseAuth = FirebaseAuth.getInstance()
        user = firebaseAuth.currentUser
        userProfileChecker = UserProfileChecker(UserProfileCheckProvider(user!!,binding.profilePhoto))
        userProfileChecker.initProfilePhoto()


        binding.toolBar.backBtn.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        binding.toolBar.signOut.setOnClickListener {
            firebaseAuth.signOut()
            val intent = Intent(requireContext(), RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.profilePhoto.setOnClickListener {
            updateImage()
        }
        return binding.root
    }

    private fun updateImage() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivityForResult(intent, KeyNums.TAKE_PHOTO_CODE)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == KeyNums.TAKE_PHOTO_CODE) {
            when (resultCode) {
                RESULT_OK -> {
                    val bitmap = data?.extras?.get("data") as Bitmap
                    binding.profilePhoto.setImageBitmap(bitmap)
                    val viewModel = ViewModelProvider(this, ProfileViewModelFactory(bitmap))[ProfileFragmentViewModel::class.java]
                    viewModel.changeProfilePhoto()
                    userProfileChecker = UserProfileChecker(UserProfileCheckProvider(user!!,binding.profilePhoto))
                    userProfileChecker.initProfilePhoto()
                }
            }
        }
    }
}