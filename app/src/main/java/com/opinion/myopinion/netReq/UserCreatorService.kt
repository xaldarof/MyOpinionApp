package com.opinion.myopinion.netReq

interface UserCreatorService {
    fun createUser(login: String, password: String,name:String,surname:String)
}