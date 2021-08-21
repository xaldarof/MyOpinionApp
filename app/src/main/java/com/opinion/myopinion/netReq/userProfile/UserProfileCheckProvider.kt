package com.opinion.myopinion.netReq.userProfile


import android.os.Looper
import android.widget.ImageView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.squareup.picasso.Picasso


class UserProfileCheckProvider(
    private var firebaseAuth: FirebaseAuth,
    private var reference: DatabaseReference,
    private var firebaseDatabase: FirebaseDatabase,
    private val profileImageView: ImageView
) : UserProfileCheckService {

    private var userPhoto = ""
    override fun initProfilePhoto() {
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference = firebaseDatabase.getReference("users")
        firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser

        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                userPhoto = snapshot.child(currentUser!!.uid).child("profileImage").value.toString()
                if (userPhoto != "") {
                    Picasso.get().load(userPhoto).fit()
                        .into(profileImageView)
                    android.os.Handler(Looper.myLooper()!!).postDelayed({
                        Picasso.get().load(userPhoto).fit()
                            .into(profileImageView)
                    }, 5000)
                }
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}