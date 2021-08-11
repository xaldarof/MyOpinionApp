package com.example.myopinion.models

import java.io.Serializable

class Report : Serializable {
    var body = ""
    var user = ""
    var date = ""
    var title = ""

    constructor()
    constructor(body: String, user: String, date: String, title: String) {
        this.body = body
        this.user = user
        this.date = date
        this.title = title
    }

    override fun toString(): String {
        return "Report(body='$body', user='$user', date='$date', title='$title')"
    }

}