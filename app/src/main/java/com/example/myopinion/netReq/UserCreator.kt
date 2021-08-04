package com.example.myopinion.netReq

import kotlin.math.log

class UserCreator(private val userCreatorProvider: UserCreatorProvider) : UserCreatorService {
    override fun createUser(login: String, password: String) {
        userCreatorProvider.createUser(login,password)
    }
}