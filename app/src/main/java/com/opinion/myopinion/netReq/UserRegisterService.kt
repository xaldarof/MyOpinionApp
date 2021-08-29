package com.opinion.myopinion.netReq

import android.widget.ProgressBar

interface UserRegisterService {
    fun login(login:String, password:String,progressBar: ProgressBar)
}