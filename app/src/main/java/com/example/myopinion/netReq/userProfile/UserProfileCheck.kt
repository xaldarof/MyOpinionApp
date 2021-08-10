package com.example.myopinion.netReq.userProfile

import androidx.lifecycle.MutableLiveData
import com.example.myopinion.models.UserModel

interface UserProfileCheck {
    fun getUserInfoFromDb(): MutableLiveData<UserModel>

    fun getUserDataSize():Int
}