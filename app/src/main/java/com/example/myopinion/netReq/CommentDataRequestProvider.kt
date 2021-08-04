package com.example.myopinion.netReq

import android.content.Context
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.myopinion.models.Comment
import com.example.myopinion.tools.FormattedDate
import com.example.myopinion.utils.KeyWords
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CommentDataRequestProvider(private val context: Context, private val fragment: Fragment) : CommentService{

    private var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var reference: DatabaseReference = database.getReference(KeyWords.FIREBAE_PATH)
    private var currentUser = FirebaseAuth.getInstance().currentUser

    override fun sendComment(editText: EditText) {
        val formattedDate = FormattedDate.formatted()
        val postId = fragment.requireArguments().getString("postId").toString()
        val comment = editText.text.toString()
        val pushId = reference.push().key.toString()
        reference.child(postId).child(KeyWords.COMMENT_PATH).child(pushId)
            .setValue(Comment(formattedDate, comment, currentUser?.photoUrl.toString(),pushId,currentUser?.displayName.toString()))
    }

}