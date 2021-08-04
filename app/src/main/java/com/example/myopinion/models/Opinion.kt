package com.example.myopinion.models

import java.io.Serializable

class Opinion : Serializable {
     var title = ""
     var type = ""
     var username = ""
     var date = ""
     var shortDescription = ""
     var exactTheme = ""
     var body = ""
     var postId = ""

    constructor()

    constructor(title: String, type: String, username: String, date: String, shorDescription: String, exactTheme: String, body: String, postId:String) {
        this.title = title
        this.type = type
        this.username = username
        this.date = date
        this.shortDescription = shorDescription
        this.exactTheme = exactTheme
        this.body = body
        this.postId = postId
    }
}