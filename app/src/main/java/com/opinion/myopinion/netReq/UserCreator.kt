package com.opinion.myopinion.netReq

class UserCreator(private val userCreatorProvider: UserCreatorProvider) : UserCreatorService {
    override fun createUser(login: String, password: String,name:String,surname:String) {
        userCreatorProvider.createUser(login,password,name,surname)
    }
}