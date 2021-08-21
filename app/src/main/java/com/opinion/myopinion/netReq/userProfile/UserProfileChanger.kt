package com.opinion.myopinion.netReq.userProfile

import android.graphics.Bitmap
import com.google.firebase.storage.StorageReference

class UserProfileChanger(private val userProfileChangerProvider: UserProfileChangerProvider) : UserProfileChangeService {

    override fun handlerUpload(bitmap: Bitmap) {
        userProfileChangerProvider.handlerUpload(bitmap)
    }

    override fun getDownloadedUrl(storageReference: StorageReference) {
       userProfileChangerProvider.getDownloadedUrl(storageReference)
    }
}