package com.example.myopinion.netReq

class UserRegister (private val userRegisterRequestProvider: UserRegisterRequestProvider) : UserRegisterService{
    override fun register(login: String, password: String) {
        userRegisterRequestProvider.register(login,password)
    }
}