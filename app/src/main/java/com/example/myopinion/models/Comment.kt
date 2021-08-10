package com.example.myopinion.models

class Comment {
    var date = ""
    var body = ""
    var commentId = ""
    var author = ""
    var emailOfSender = ""

    constructor()
    constructor(date: String, body: String,emailOfSender:String,commentId:String,author:String) {
        this.date = date
        this.body = body
        this.emailOfSender = emailOfSender
        this.commentId = commentId
        this.author = author
    }

    override fun toString(): String {
        return "Comment(date='$date', body='$body', imageUrl='$emailOfSender', commentId='$commentId', author='$author')"
    }

}