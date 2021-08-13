package com.example.myopinion.netReq.userProfile

import com.example.myopinion.models.UserModel
import kotlinx.coroutines.flow.MutableStateFlow

interface UserProfileCheck {
    suspend fun getUserInfoFromDb() : MutableStateFlow<UserModel>

    fun getUserDataSize():Int
}