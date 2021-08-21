package com.opinion.myopinion.repository.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

open class OpinionEntity : RealmObject() {
    @PrimaryKey
    @Required
    var title: String? = null

    var username: String? = null
    var date: String? = null
    var shortDescription: String? = null
    var exactTheme: String? = null
    var body: String? = null
    var type: String? = null
    var postId: String? = null
}