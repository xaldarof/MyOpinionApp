package com.opinion.myopinion.netReq.userProfile

class UserInformationFiller(private val userInformationFillerServiceProvider: UserInformationFillerServiceProvider) : UserInformationFillerService {
    override fun fillUserData(name: String, surname: String) {
        userInformationFillerServiceProvider.fillUserData(name,surname)
    }
}