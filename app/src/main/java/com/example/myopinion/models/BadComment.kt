package com.example.myopinion.models

class BadComment {
    private var postId= ""
    private var commentId = ""
    private var body = ""
    private var date = ""

    constructor()
    constructor(postId: String, commentId: String, body: String, date: String) {
        this.postId = postId
        this.commentId = commentId
        this.body = body
        this.date = date
    }

    override fun toString(): String {
        return "BadComment(postId='$postId', commentId='$commentId', body='$body', date='$date')"
    }


}