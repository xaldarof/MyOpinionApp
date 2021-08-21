package com.opinion.myopinion.models

import java.io.Serializable

class Notification : Serializable{

     var title = ""
     var body = ""
     var date = ""
    var image= ""

    constructor()
    constructor(title: String, body: String, date: String,image:String) {
        this.title = title
        this.body = body
        this.date = date
        this.image = image
    }

    override fun toString(): String {
        return "Notification(title='$title', body='$body', date='$date', image='$image')"
    }

}