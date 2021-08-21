package com.opinion.myopinion.netReq.userProfile

class UserProfileChecker(private val userProfileCheckProvider: UserProfileCheckProvider) : UserProfileCheckService {
    override fun initProfilePhoto() {
        userProfileCheckProvider.initProfilePhoto()
    }
}