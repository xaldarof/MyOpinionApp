package com.example.myopinion.netReq.userProfile


import android.os.Looper
import android.util.Log
import android.widget.ImageView
import com.example.myopinion.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.squareup.picasso.Picasso


class UserProfileCheckProvider (private var firebaseAuth: FirebaseAuth,
                                private var reference: DatabaseReference,
                                private var firebaseDatabase: FirebaseDatabase,
                                private val profileImageView: ImageView
                                ) : UserProfileCheckService {

    override fun initProfilePhoto() {
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference = firebaseDatabase.getReference("users")
        firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser

        reference.addValueEventListener(object :ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userPhoto = snapshot.child(currentUser!!.uid).child("profileImage").value
                Log.d("image",userPhoto.toString())

               if (userPhoto.toString().isNotEmpty()){
                   Picasso.get().load(userPhoto.toString()).fit()
                       .into(profileImageView)
                   android.os.Handler(Looper.myLooper()!!).postDelayed({
                       Picasso.get().load(userPhoto.toString()).fit()
                           .into(profileImageView)
                   }, 5000)
               }
               else if (userPhoto == null) {
                   profileImageView.setImageResource(R.drawable.ic_baseline_photo_camera_24)
               }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

}