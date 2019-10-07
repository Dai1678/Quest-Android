package com.dai1678.quest.util

import android.content.Context
import com.dai1678.quest.App

object PreferenceService {

    fun registerLoggedInDoctorId(doctorId: String) {
        val context = App.instance
        val preferences = context.getSharedPreferences(App.PREFERENCES_NAME, Context.MODE_PRIVATE)
        preferences.edit().putString("doctorId", doctorId).apply()
    }

    fun getLoggedInDoctorId(): String? {
        val context = App.instance
        val preferences = context.getSharedPreferences(App.PREFERENCES_NAME, Context.MODE_PRIVATE)
        return preferences.getString("doctorId", null)
    }

    fun deleteLoggedInDoctorId() {
        val context = App.instance
        val preferences = context.getSharedPreferences(App.PREFERENCES_NAME, Context.MODE_PRIVATE)
        preferences.edit().remove("doctorId").apply()
    }
}
