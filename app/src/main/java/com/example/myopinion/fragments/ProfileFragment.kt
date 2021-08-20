package com.example.myopinion.fragments

import android.app.Activity.MODE_PRIVATE
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myopinion.R
import com.example.myopinion.databinding.FragmentProfileBinding
import com.example.myopinion.helpers.PasswordCheck
import com.example.myopinion.helpers.PasswordSetter
import com.example.myopinion.helpers.ProfileBottomSheetDialogShower
import com.example.myopinion.helpers.SignOut
import com.example.myopinion.netReq.userProfile.UserProfileCheckProvider
import com.example.myopinion.netReq.userProfile.UserProfileChecker
import com.example.myopinion.netReq.userProfile.UserProfileInfo
import com.example.myopinion.netReq.userProfile.UserProfileInfoCheckProvider
import com.example.myopinion.presentation.registration.RegisterActivity
import com.example.myopinion.repository.FavoriteOpinionCacheDataSource
import com.example.myopinion.repository.FavoriteOpinionDataSource
import com.example.myopinion.utils.KeyNums
import com.example.myopinion.viewmodel.ProfileFragmentViewModel
import com.example.myopinion.viewmodel.ProfileViewModelFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import io.realm.Realm
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private var user: FirebaseUser? = null
    private lateinit var userProfileChecker: UserProfileChecker
    private lateinit var firebaseDatabase : FirebaseDatabase
    private lateinit var referenceFirebaseDatabase : DatabaseReference
    private val realm = Realm.getDefaultInstance()
    private val favoriteOpinionDataSource = FavoriteOpinionDataSource(FavoriteOpinionCacheDataSource(realm))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        initActions()
        val passwordSetter = PasswordSetter(requireContext(),this)
        passwordSetter.init(binding.setPasswordBtn)
        PasswordCheck.PasswordChecker(requireContext(),binding.passwordInfoImg).init()

        firebaseAuth = FirebaseAuth.getInstance()
        user = firebaseAuth.currentUser
        firebaseDatabase = FirebaseDatabase.getInstance()
        referenceFirebaseDatabase = firebaseDatabase.reference

        userProfileChecker = UserProfileChecker(UserProfileCheckProvider(firebaseAuth,referenceFirebaseDatabase,firebaseDatabase,binding.profilePhoto))
        userProfileChecker.initProfilePhoto()

        val userProfileInfo = UserProfileInfo(UserProfileInfoCheckProvider(firebaseAuth,referenceFirebaseDatabase,
            firebaseDatabase, favoriteOpinionDataSource))
        CoroutineScope(Dispatchers.Main).launch {
            userProfileInfo.getUserInfoFromDb().collect {
                binding.tvName.text = it.name.plus(" ${it.surname}")
                binding.tvDateOfRegister.text = it.dateOfRegister
                binding.tvEmail.text = user!!.email
            }
        }
        binding.tvFavoritesCount.text = userProfileInfo.getUserDataSize().toString()

        return binding.root
    }

    private fun initActions(){
        binding.toolBar.backBtn.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        binding.toolBar.signOut.setOnClickListener {
            SignOut.SignOutHelper(firebaseAuth,requireContext(),requireActivity()).signOut()
        }
        binding.profilePhoto.setOnClickListener {
            updateImage()
        }
        binding.fillBtn.setOnClickListener {
            ProfileBottomSheetDialogShower(requireContext(),this).show()
        }
        binding.readSavedBtn.setOnClickListener {
            val savedFragment = SavedFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.layout, savedFragment)
                .addToBackStack(null).commit()
        }

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
                    initViewModel(bitmap)
                    userProfileChecker = UserProfileChecker(UserProfileCheckProvider(firebaseAuth,referenceFirebaseDatabase,firebaseDatabase,binding.profilePhoto))
                    userProfileChecker.initProfilePhoto()
                }
            }
        }
    }
    private fun initViewModel(bitmap: Bitmap){
        val viewModel = ViewModelProvider(this, ProfileViewModelFactory(bitmap,firebaseAuth,referenceFirebaseDatabase,firebaseDatabase))[ProfileFragmentViewModel::class.java]
        viewModel.changeProfilePhoto()
    }
}