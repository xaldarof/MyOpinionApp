package com.opinion.myopinion.models

import java.io.Serializable

class UserModel : Serializable {
   var name = ""
   var surname = ""
   var hobby = ""
   var dateOfRegister = ""
   var profileImage = ""
    var uid = ""

    constructor()
    constructor(
        name: String,
        surname: String,
        hobby: String,
        dateOfRegister: String,
        profileImage: String,
        uid: String
    ) {
        this.name = name
        this.surname = surname
        this.hobby = hobby
        this.dateOfRegister = dateOfRegister
        this.profileImage = profileImage
        this.uid = uid
    }


    override fun toString(): String {
        return "UserModel(name='$name', surname='$surname', hobby='$hobby', dateOfRegister='$dateOfRegister', profileImage='$profileImage', uid='$uid')"
    }

}