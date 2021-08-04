package com.example.myopinion.netReq.userProfile

import android.os.Handler
import android.os.Looper

class UserProfileChecker(private val userProfileCheckProvider: UserProfileCheckProvider) : UserProfileCheckService {
    override fun initProfilePhoto() {
        userProfileCheckProvider.initProfilePhoto()
    }
}