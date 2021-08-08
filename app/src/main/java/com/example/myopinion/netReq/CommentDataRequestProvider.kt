package com.example.myopinion.netReq

import android.content.Context
import android.util.Log
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.myopinion.models.Comment
import com.example.myopinion.tools.FormattedDate
import com.example.myopinion.utils.KeyWords
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class CommentDataRequestProvider(private val context: Context, private val fragment: Fragment) : CommentService{

    private var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var reference: DatabaseReference = database.getReference(KeyWords.FIREBAE_PATH)
    private var currentUser = FirebaseAuth.getInstance().currentUser
    private var image = ""
    private var name = ""

    override fun sendComment(editText: EditText) {
        val formattedDate = FormattedDate.formatted()
        val postId = fragment.requireArguments().getString("postId").toString()
        val comment = editText.text.toString()
        val pushId = reference.push().key.toString()
        val img = "https://image.flaticon.com/icons/png/512/3296/3296116.png"

        val databaseForCommentImage: FirebaseDatabase = FirebaseDatabase.getInstance()
        val referenceForCommentImage: DatabaseReference = databaseForCommentImage.getReference("users")

        referenceForCommentImage.addValueEventListener(object :ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //we will take image url from user profile
                image = snapshot.child(currentUser!!.uid).child("profileImage").value.toString()
                name = snapshot.child(currentUser!!.uid).child("name").value.toString()

                Log.d("image",image)
                reference.child(postId).child(KeyWords.COMMENT_PATH).child(pushId)
                    .setValue(Comment(formattedDate, comment,img,pushId,currentUser?.displayName.toString()))
                //  .setValue(Comment(formattedDate, comment,image,pushId,currentUser?.displayName.toString()))
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

}