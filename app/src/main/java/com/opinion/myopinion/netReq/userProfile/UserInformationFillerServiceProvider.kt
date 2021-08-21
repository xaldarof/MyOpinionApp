package com.opinion.myopinion.netReq.userProfile

import com.opinion.myopinion.helpers.FormattedDate
import com.opinion.myopinion.models.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UserInformationFillerServiceProvider(private var firebaseAuth: FirebaseAuth,private var databaseReference: DatabaseReference,private var firebaseDatabase: FirebaseDatabase) : UserInformationFillerService {


    override fun fillUserData(name: String, surname: String) {
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.getReference("users")
        firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser?.uid.toString()
        val formattedDate = FormattedDate.formatted()

        databaseReference.child(currentUser).setValue(UserModel(name,surname,"","",formattedDate,"",currentUser))
    }
}