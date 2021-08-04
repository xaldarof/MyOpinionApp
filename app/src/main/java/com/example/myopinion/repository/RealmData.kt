package com.example.myopinion.repository

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class RealmData : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val build = RealmConfiguration.Builder()
            .schemaVersion(1)
            .allowWritesOnUiThread(true)
            .allowQueriesOnUiThread(true)
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(build)
    }
}