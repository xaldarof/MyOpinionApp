package com.opinion.myopinion.helpers

import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.opinion.myopinion.models.Comment

interface CommentCounter {

    fun getCommentCount(postId: String, textView: TextView): String

    class Base : CommentCounter {

        override fun getCommentCount(postId: String, textView: TextView): String {
            val firebaseDatabase = FirebaseDatabase.getInstance()
            val databaseReference = firebaseDatabase.getReference("opinion")
            val count = ArrayList<Comment>()
            databaseReference.child(postId).child("comments").addValueEventListener(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val child = snapshot.children
                    for (i in child) {
                        val value = i.getValue(Comment::class.java)

                        count.add(value!!)
                    }
                    textView.text = count.size.toString()
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
            return count.size.toString()
        }
    }
}
