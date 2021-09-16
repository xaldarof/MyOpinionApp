package com.opinion.myopinion.presentation

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.opinion.myopinion.R
import com.opinion.myopinion.databinding.ActivityProfileBinding
import com.opinion.myopinion.fragments.SavedFragment
import com.opinion.myopinion.helpers.PasswordCheck
import com.opinion.myopinion.helpers.PasswordSetter
import com.opinion.myopinion.helpers.ProfileBottomSheetDialogShower
import com.opinion.myopinion.helpers.SignOut
import com.opinion.myopinion.netReq.userProfile.UserProfileCheckProvider
import com.opinion.myopinion.netReq.userProfile.UserProfileChecker
import com.opinion.myopinion.netReq.userProfile.UserProfileInfo
import com.opinion.myopinion.netReq.userProfile.UserProfileInfoCheckProvider
import com.opinion.myopinion.repository.FavoriteOpinionCacheDataSource
import com.opinion.myopinion.repository.FavoriteOpinionDataSource
import com.opinion.myopinion.viewmodel.ProfileFragmentViewModel
import com.opinion.myopinion.viewmodel.ProfileViewModelFactory
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import io.realm.Realm
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private var user: FirebaseUser? = null
    private lateinit var userProfileChecker: UserProfileChecker
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var referenceFirebaseDatabase: DatabaseReference
    private val realm = Realm.getDefaultInstance()
    private val favoriteOpinionDataSource =
        FavoriteOpinionDataSource(FavoriteOpinionCacheDataSource(realm))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initActions()
        val passwordSetter = PasswordSetter(this)
        passwordSetter.init(binding.setPasswordBtn)
        PasswordCheck.PasswordChecker(this, binding.passwordInfoImg).init()

        firebaseAuth = FirebaseAuth.getInstance()
        user = firebaseAuth.currentUser
        firebaseDatabase = FirebaseDatabase.getInstance()
        referenceFirebaseDatabase = firebaseDatabase.reference

        userProfileChecker = UserProfileChecker(
            UserProfileCheckProvider(firebaseAuth, referenceFirebaseDatabase, firebaseDatabase, binding.profilePhoto)
        )
        userProfileChecker.initProfilePhoto()

        val userProfileInfo = UserProfileInfo(
            UserProfileInfoCheckProvider(firebaseAuth, referenceFirebaseDatabase, firebaseDatabase, favoriteOpinionDataSource)
        )

        CoroutineScope(Dispatchers.Main).launch {
            userProfileInfo.serUserInfoToDb(binding.tvName, binding.tvDateOfRegister, binding.tvEmail)
        }

        binding.tvFavoritesCount.text = userProfileInfo.getUserDataSize().toString()

    }

    private fun initActions() {
        binding.profilePhoto.setOnClickListener {
            CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).start(this)
        }

        binding.toolBar.backBtn.setOnClickListener {
            finish()
        }
        binding.toolBar.signOut.setOnClickListener {
            SignOut.SignOutHelper(firebaseAuth, this, this).signOut()
        }

        binding.fillBtn.setOnClickListener {
            ProfileBottomSheetDialogShower(this, this).show()
        }
        binding.readSavedBtn.setOnClickListener {
            val savedFragment = SavedFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.profile_layout, savedFragment)
                .addToBackStack(null).commit()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            val cropImage = CropImage.getActivityResult(data)
            val uri = cropImage.uri
            binding.profilePhoto.setImageURI(uri)
            val bitmap = (binding.profilePhoto.drawable as BitmapDrawable).bitmap
            initViewModel(bitmap)
            userProfileChecker =
                UserProfileChecker(UserProfileCheckProvider
                    (firebaseAuth,referenceFirebaseDatabase,firebaseDatabase,binding.profilePhoto))
            userProfileChecker.initProfilePhoto()

        }
    }
    private fun initViewModel(bitmap: Bitmap) {
        val viewModel = ViewModelProvider(this,
            ProfileViewModelFactory(bitmap, firebaseAuth, referenceFirebaseDatabase, firebaseDatabase)
        )[ProfileFragmentViewModel::class.java]
        viewModel.changeProfilePhoto()
    }
}