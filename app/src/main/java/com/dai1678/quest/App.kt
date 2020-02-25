package com.dai1678.quest

import android.app.Application

/**
 * Applicationのインスタンスを保持するクラス
 */
class App : Application() {

    companion object {
        lateinit var instance: Application private set
        const val PREFERENCES_NAME = "DataStore"
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
