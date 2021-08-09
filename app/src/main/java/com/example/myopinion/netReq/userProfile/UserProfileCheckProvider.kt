package com.example.myopinion.netReq.userProfile

import android.os.Looper
import android.util.Log
import android.widget.ImageView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

class UserProfileCheckProvider (private var firebaseAuth: FirebaseAuth,private var reference: DatabaseReference,private var firebaseDatabase: FirebaseDatabase,private val profileImageView: ImageView) : UserProfileCheckService {
    override fun initProfilePhoto() {
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference = firebaseDatabase.getReference("users")
        firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser

        reference.addValueEventListener(object :ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userPhoto = snapshot.child(currentUser!!.uid).child("profileImage").value
                Log.d("image",userPhoto.toString())

                Picasso.get().load(userPhoto.toString()).transform(CropCircleTransformation())
                    .into(profileImageView)
                android.os.Handler(Looper.myLooper()!!).postDelayed({
                    Picasso.get().load(userPhoto.toString()).transform(CropCircleTransformation())
                        .into(profileImageView)
                }, 5000)
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

}