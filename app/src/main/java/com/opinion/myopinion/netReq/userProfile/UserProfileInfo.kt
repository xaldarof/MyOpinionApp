package com.opinion.myopinion.netReq.userProfile

import com.opinion.myopinion.models.UserModel
import kotlinx.coroutines.flow.MutableStateFlow

class UserProfileInfo(private val userProfileInfoCheckProvider: UserProfileInfoCheckProvider):UserProfileCheck {

    override suspend fun getUserInfoFromDb(): MutableStateFlow<UserModel> {
        return userProfileInfoCheckProvider.getUserInfoFromDb()
    }

    override fun getUserDataSize(): Int {
       return userProfileInfoCheckProvider.getUserDataSize()
    }
}