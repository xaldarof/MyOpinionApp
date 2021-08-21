package com.opinion.myopinion.netRes

import androidx.lifecycle.MutableLiveData
import com.opinion.myopinion.models.Opinion
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ViewsCloudDataSource(private var firebaseDatabase: FirebaseDatabase,
                           private var firebaseAuth: FirebaseAuth) : ViewCloudDataSourceService {

    private val viewsCountLiveData = MutableLiveData<List<String>>()
    private lateinit var databaseReference : DatabaseReference

    override fun getViewsCount(opinion: Opinion):MutableLiveData<List<String>> {
        firebaseDatabase = FirebaseDatabase.getInstance()

        firebaseAuth = FirebaseAuth.getInstance()
        databaseReference = firebaseDatabase.getReference("opinion")
        val currentUser = firebaseAuth.currentUser?.uid.toString()

        databaseReference.child(opinion.postId).child("views").child(currentUser).setValue(" ")
        val list = ArrayList<String>()
        databaseReference.child(opinion.postId).child("views").addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                for (value in snapshot.children){
                    val count = value.getValue(true)
                    list.add(count.toString())
                }
               viewsCountLiveData.value = list
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
        return viewsCountLiveData
    }
}