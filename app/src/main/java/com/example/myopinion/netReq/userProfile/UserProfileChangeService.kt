package com.example.myopinion.netReq.userProfile

import android.graphics.Bitmap
import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

interface UserProfileChangeService {
    fun handlerUpload(bitmap: Bitmap)

    fun getDownloadedUrl(storageReference: StorageReference)

   // fun setUserProfileUrl(uri: Uri)
}