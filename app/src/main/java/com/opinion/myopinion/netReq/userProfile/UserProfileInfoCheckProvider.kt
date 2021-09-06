package com.opinion.myopinion.netReq.userProfile

import android.widget.TextView
import com.opinion.myopinion.models.UserModel
import com.opinion.myopinion.repository.FavoriteOpinionDataSource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.coroutines.flow.MutableStateFlow

class UserProfileInfoCheckProvider(private val firebaseAuth: FirebaseAuth,
                                   private var databaseReference: DatabaseReference,
                                   private var firebaseDatabase: FirebaseDatabase,
                                   private val favoriteOpinionDataSource: FavoriteOpinionDataSource
                                   ): UserProfileCheck {
    private var name = ""
    private var surname = ""
    private var birthDay = ""
    private var hobby = ""
    private var dateOfRegister = ""
    private var profileImage = ""
    private var uid = ""

    override suspend fun serUserInfoToDb(nameTv: TextView,dateRegTv:TextView) {
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.getReference("users")
        val userUid = firebaseAuth.currentUser?.uid.toString()

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                name = snapshot.child(userUid).child("name").value.toString()
                birthDay = snapshot.child(userUid).child("birthDay").value.toString()
                surname = snapshot.child(userUid).child("surname").value.toString()
                hobby = snapshot.child(userUid).child("hobby").value.toString()
                dateOfRegister = snapshot.child(userUid).child("dateOfRegister").value.toString()
                profileImage = snapshot.child(userUid).child("profileImage").value.toString()
                uid = firebaseAuth.currentUser?.uid.toString()

                nameTv.text = name.plus(" $surname")
                dateRegTv.text = dateOfRegister

            }
            override fun onCancelled(error: DatabaseError) {}
        })
    }

    override fun getUserDataSize(): Int {
        return  favoriteOpinionDataSource.getFavoriteOpinions().size
    }
}
