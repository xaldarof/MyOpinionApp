package com.example.myopinion.netRes

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.example.myopinion.models.Comment
import com.example.myopinion.utils.KeyWords
import com.google.firebase.database.*

class CommentsCloudDataSource(private val fragment:Fragment) : CommentsService {
    private var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var reference: DatabaseReference = database.getReference(KeyWords.FIREBAE_PATH)
    private val list = ArrayList<Comment>()
    private val commentLiveData =  MutableLiveData<List<Comment>>()
    override fun getComments(): MutableLiveData<List<Comment>> {
        val postId = fragment.requireArguments().getString("postId").toString()
        reference.child(postId).child(KeyWords.COMMENT_PATH).addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                val children = snapshot.children
                for (child in children){
                    val value = child.getValue(Comment::class.java)

                    if (value!=null) {
                        list.add(value)
                    }
                }
                commentLiveData.postValue(list)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        return commentLiveData
    }


}