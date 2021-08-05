package com.example.myopinion.helpers

import java.io.Serializable

class UserModel : Serializable {
   var name = ""
   var surname = ""
   var hobby = ""
   var birthDay = ""
   var dateOfRegister = ""

    constructor()
    constructor(
        name: String,
        surname: String,
        hobby: String,
        birthDay: String,
        dateOfRegister: String,
    ) {
        this.name = name
        this.surname = surname
        this.hobby = hobby
        this.birthDay = birthDay
        this.dateOfRegister = dateOfRegister
    }

    override fun toString(): String {
        return "UserModel(name='$name', surname='$surname', hobby='$hobby', birthDay='$birthDay', date='$dateOfRegister')"
    }
}