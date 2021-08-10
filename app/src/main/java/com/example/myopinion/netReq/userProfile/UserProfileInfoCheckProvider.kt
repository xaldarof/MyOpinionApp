package com.example.myopinion.netReq.userProfile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.myopinion.models.UserModel
import com.example.myopinion.repository.FavoriteOpinionDataSource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class UserProfileInfoCheckProvider(private val firebaseAuth: FirebaseAuth,
                                   private var databaseReference: DatabaseReference,
                                   private var firebaseDatabase: FirebaseDatabase,
                                   private val favoriteOpinionDataSource: FavoriteOpinionDataSource
                                   ): UserProfileCheck {

    private var name = ""
    private var birthDay = ""
    private var surname = ""
    private var hobby = ""
    private var dateOfRegister = ""
    private var profileImage = ""
    private var uid = ""
    private val userInfoLiveData = MutableLiveData<UserModel>()


    override fun getUserInfoFromDb() : MutableLiveData<UserModel> {
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.getReference("users")
        val userUid = firebaseAuth.currentUser?.uid.toString()

        databaseReference.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                name = snapshot.child(userUid).child("name").value.toString()
                birthDay = snapshot.child(userUid).child("birthDay").value.toString()
                surname = snapshot.child(userUid).child("surname").value.toString()
                hobby = snapshot.child(userUid).child("hobby").value.toString()
                dateOfRegister = snapshot.child(userUid).child("dateOfRegister").value.toString()
                profileImage = snapshot.child(userUid).child("profileImage").value.toString()
                uid = firebaseAuth.currentUser?.uid.toString()

                Log.d("data2","USER INFO IN PROVIDER = $name")
                userInfoLiveData.value = UserModel(name,surname,hobby,birthDay,dateOfRegister,profileImage,uid)
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
        return userInfoLiveData
    }

    override fun getUserDataSize():Int{
        return  favoriteOpinionDataSource.getFavoriteOpinions().size
    }
}