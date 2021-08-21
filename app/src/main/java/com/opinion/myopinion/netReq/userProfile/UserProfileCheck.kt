package com.opinion.myopinion.netReq.userProfile

import com.opinion.myopinion.models.UserModel
import kotlinx.coroutines.flow.MutableStateFlow

interface UserProfileCheck {
    suspend fun getUserInfoFromDb() : MutableStateFlow<UserModel>

    fun getUserDataSize():Int
}