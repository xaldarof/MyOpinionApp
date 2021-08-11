package com.example.myopinion.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myopinion.databinding.ActivityReadingBinding
import com.example.myopinion.models.Opinion
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ReadingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReadingBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = intent.getSerializableExtra("opinion") as Opinion

        binding.toolBar.activityTitle.text = intent.title
        binding.bodyTv.text = intent.body

        firebaseDatabase = FirebaseDatabase.getInstance()
        firebaseAuth = FirebaseAuth.getInstance()
        databaseReference = firebaseDatabase.getReference("opinion")

        val currentUser = firebaseAuth.currentUser?.uid.toString()

        databaseReference.child(intent.postId).child("views").child(currentUser).setValue(" ")
        val list = ArrayList<String>()
        databaseReference.child(intent.postId).child("views").addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                for (value in snapshot.children){
                    val count = value.getValue(true)
                    list.add(count.toString())
                }
                binding.toolBar.viewCounter.text = list.size.toString()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
        binding.toolBar.backBtn.setOnClickListener {
            finish()
        }
    }
}