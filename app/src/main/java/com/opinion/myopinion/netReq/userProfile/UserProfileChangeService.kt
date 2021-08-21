package com.opinion.myopinion.netReq.userProfile

import android.graphics.Bitmap
import com.google.firebase.storage.StorageReference

interface UserProfileChangeService {
    fun handlerUpload(bitmap: Bitmap)

    fun getDownloadedUrl(storageReference: StorageReference)

   // fun setUserProfileUrl(uri: Uri)
}