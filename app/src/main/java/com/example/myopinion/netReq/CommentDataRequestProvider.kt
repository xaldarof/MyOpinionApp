package com.example.myopinion.netReq

import android.content.Context
import android.os.Looper
import android.util.Log
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.myopinion.models.Comment
import com.example.myopinion.tools.FormattedDate
import com.example.myopinion.utils.KeyWords
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

class CommentDataRequestProvider(private val context: Context, private val fragment: Fragment) : CommentService{

    private var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var reference: DatabaseReference = database.getReference(KeyWords.FIREBAE_PATH)
    private var currentUser = FirebaseAuth.getInstance().currentUser
    private var image = ""

    override fun sendComment(editText: EditText) {
        val formattedDate = FormattedDate.formatted()
        val postId = fragment.requireArguments().getString("postId").toString()
        val comment = editText.text.toString()
        val pushId = reference.push().key.toString()

        val databaseForCommentImage: FirebaseDatabase = FirebaseDatabase.getInstance()
        val referenceForCommentImage: DatabaseReference = databaseForCommentImage.getReference("users")

        referenceForCommentImage.addValueEventListener(object :ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                image = snapshot.child(currentUser!!.uid).child("profileImage").value.toString()
                Log.d("image",image)
                reference.child(postId).child(KeyWords.COMMENT_PATH).child(pushId)
                    .setValue(Comment(formattedDate, comment,image,pushId,currentUser?.displayName.toString()))
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }

}