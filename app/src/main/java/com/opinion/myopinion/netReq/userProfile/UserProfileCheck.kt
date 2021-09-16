package com.opinion.myopinion.netReq.userProfile

import android.widget.TextView
import com.opinion.myopinion.models.UserModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.w3c.dom.Text

interface UserProfileCheck {
    suspend fun serUserInfoToDb(nameTv:TextView,dateRegTv:TextView,emailTv:TextView)

    fun getUserDataSize():Int
}