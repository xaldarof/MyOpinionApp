package com.opinion.myopinion.netReq

import android.widget.ProgressBar

class UserLogin (private val userLoginRequestProvider: UserLoginRequestProvider) : UserRegisterService{
    override fun login(login: String, password: String,progressBar: ProgressBar) {
        userLoginRequestProvider.login(login,password,progressBar)
    }
}