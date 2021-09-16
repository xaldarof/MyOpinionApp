package com.opinion.myopinion.netReq.userProfile

import android.widget.TextView

class UserProfileInfo(private val userProfileInfoCheckProvider: UserProfileInfoCheckProvider):UserProfileCheck {

    override suspend fun serUserInfoToDb(nameTv: TextView, dateRegTv: TextView,emailTv:TextView) {
        userProfileInfoCheckProvider.serUserInfoToDb(nameTv,dateRegTv,emailTv)
    }


    override fun getUserDataSize(): Int {
       return userProfileInfoCheckProvider.getUserDataSize()
    }
}