package com.opinion.myopinion.netRes

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.opinion.myopinion.models.Opinion
import com.opinion.myopinion.utils.KeyWords
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class OpinionsCloudDataSource : OpinionsService {

    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val reference = firebaseDatabase.getReference(KeyWords.FIREBASE_PATH)
    private val opinionLiveData =  MutableLiveData<List<Opinion>>()
    private val opinionList = ArrayList<Opinion>()

    override fun getOpinions(): MutableLiveData<List<Opinion>> {
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                opinionList.clear()
                val children = snapshot.children
                for (value in children) {
                    val opinion = value.getValue(Opinion::class.java)
                    if (opinion != null) {
                        opinionList.add(opinion)
                    }
                }
                opinionLiveData.value = opinionList
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("errors",error.message)
            }
        })
        return opinionLiveData
    }


}