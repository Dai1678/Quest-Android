package com.dai1678.quest.util

import android.content.Context
import com.dai1678.quest.Quest

object PreferenceService {

    fun getLoggedInHospitalId(): String? {
        val context = Quest.instance
        val preferences = context.getSharedPreferences("DataStore", Context.MODE_PRIVATE)
        return preferences.getString("hospitalId", null)
    }

    fun getAuthToken(): String? {
        val context = Quest.instance
        val preferences = context.getSharedPreferences("DataStore", Context.MODE_PRIVATE)
        val token = preferences.getString("token", null)
        return if (token == null) {
            null
        } else {
            "JWT $token"
        }
    }
}
