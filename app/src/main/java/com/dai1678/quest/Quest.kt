package com.dai1678.quest

import android.app.Application

class Quest: Application() {
    companion object {
        lateinit var instance: Application private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
