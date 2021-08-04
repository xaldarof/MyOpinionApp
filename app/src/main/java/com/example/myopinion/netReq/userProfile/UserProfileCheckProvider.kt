package com.example.myopinion.netReq.userProfile

import android.os.Looper
import android.widget.ImageView
import com.google.firebase.auth.FirebaseUser
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation


class UserProfileCheckProvider (private val firebaseUser: FirebaseUser,private val profileImageView: ImageView) : UserProfileCheckService {
    override fun initProfilePhoto() {
        Picasso.get().load(firebaseUser.photoUrl).transform(CropCircleTransformation()).into(profileImageView)
        android.os.Handler(Looper.myLooper()!!).postDelayed({
            Picasso.get().load(firebaseUser.photoUrl).transform(CropCircleTransformation()).into(profileImageView)
        },5000)
    }

}