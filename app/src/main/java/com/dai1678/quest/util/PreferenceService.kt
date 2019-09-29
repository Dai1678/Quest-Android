package com.dai1678.quest.util

import android.content.Context
import com.dai1678.quest.App

object PreferenceService {

    fun getLoggedInDoctorId(): String? {
        val context = App.instance
        val preferences = context.getSharedPreferences("DataStore", Context.MODE_PRIVATE)
        return preferences.getString("doctorId", null)
    }

    fun getAuthToken(): String? {
        val context = App.instance
        val preferences = context.getSharedPreferences("DataStore", Context.MODE_PRIVATE)
        val token = preferences.getString("token", null)
        return if (token == null) {
            null
        } else {
            "JWT $token"
        }
    }
}
