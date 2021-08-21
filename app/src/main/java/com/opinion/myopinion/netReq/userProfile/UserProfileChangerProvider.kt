package com.opinion.myopinion.netReq.userProfile

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.ByteArrayOutputStream

class UserProfileChangerProvider(
    private val user: FirebaseUser,
    private var firebaseDatabase: FirebaseDatabase,
    private var reference: DatabaseReference,
    private var firebaseAuth: FirebaseAuth
) : UserProfileChangeService {
    override fun handlerUpload(bitmap: Bitmap) {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)

        val storageReference = FirebaseStorage.getInstance().reference
            .child("profile_photos")
            .child("${user.uid}.jpeg")

        storageReference.putBytes(byteArrayOutputStream.toByteArray())
            .addOnSuccessListener {
                getDownloadedUrl(storageReference)
            }
    }

    override fun getDownloadedUrl(storageReference: StorageReference) {
        storageReference.downloadUrl.addOnSuccessListener {
            Log.d("dataPhoto", "$it")

            setUserProfileUrl(it)
        }
    }

    private fun setUserProfileUrl(url: Uri) {
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        val hashMapForImageUpdate = HashMap<String, Any?>()
        hashMapForImageUpdate["profileImage"] = url.toString()
        reference = firebaseDatabase.getReference("users")
        reference.child(firebaseAuth.currentUser!!.uid).updateChildren(hashMapForImageUpdate)
    }
}
