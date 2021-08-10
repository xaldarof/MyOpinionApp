package com.example.myopinion.netReq.userProfile

import androidx.lifecycle.MutableLiveData
import com.example.myopinion.models.UserModel

class UserProfileInfo(private val userProfileInfoCheckProvider: UserProfileInfoCheckProvider):UserProfileCheck {
    override fun getUserInfoFromDb(): MutableLiveData<UserModel> {
        return userProfileInfoCheckProvider.getUserInfoFromDb()
    }

    override fun getUserDataSize(): Int {
       return userProfileInfoCheckProvider.getUserDataSize()
    }
}