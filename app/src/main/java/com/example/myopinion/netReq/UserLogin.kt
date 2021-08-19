package com.example.myopinion.netReq

class UserLogin (private val userLoginRequestProvider: UserLoginRequestProvider) : UserRegisterService{
    override fun login(login: String, password: String) {
        userLoginRequestProvider.login(login,password)
    }
}