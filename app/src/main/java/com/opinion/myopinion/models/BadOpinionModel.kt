package com.opinion.myopinion.models

import java.io.Serializable

class BadOpinionModel : Serializable {
    var uid = ""
    var title = ""
    var reporter = ""

    constructor()
    constructor(uid: String, title: String, reporter: String) {
        this.uid = uid
        this.title = title
        this.reporter = reporter
    }

    override fun toString(): String {
        return "BadOpinionModel(uid='$uid', title='$title', reporter='$reporter')"
    }
}