package com.example.myopinion.netReq

import android.app.Activity
import android.content.Intent
import com.example.myopinion.netReq.userProfile.UserInformationFiller
import com.example.myopinion.netReq.userProfile.UserInformationFillerServiceProvider
import com.example.myopinion.presentation.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UserCreatorProvider(private val firebaseAuth: FirebaseAuth,private val activity: Activity,private val databaseReference: DatabaseReference,firebaseDatabase: FirebaseDatabase) : UserCreatorService {

    private val userInformationFiller = UserInformationFiller(UserInformationFillerServiceProvider(firebaseAuth,databaseReference,firebaseDatabase))

    override fun createUser(login: String, password: String,name:String,surname:String) {
        if (login.isNotEmpty() && password.isNotEmpty()) {
            firebaseAuth.createUserWithEmailAndPassword(login, password).addOnCompleteListener {
                if (it.isSuccessful){
                    val intent = Intent(activity, MainActivity::class.java)
                    activity.startActivity(intent)
                    activity.finish()
                    userInformationFiller.fillUserData(name,surname)
                }
            }

        }
    }
}
