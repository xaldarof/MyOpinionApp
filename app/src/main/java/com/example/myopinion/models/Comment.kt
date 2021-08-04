package com.example.myopinion.models

class Comment {
    var date = ""
    var body = ""
    var imageUrl = ""
    var commentId = ""
    var author = ""

    constructor()
    constructor(date: String, body: String,image:String,commentId:String,author:String) {
        this.date = date
        this.body = body
        this.imageUrl = image
        this.commentId = commentId
        this.author = author
    }

    override fun toString(): String {
        return "Comment(date='$date', body='$body', imageUrl='$imageUrl', commentId='$commentId', author='$author')"
    }

}