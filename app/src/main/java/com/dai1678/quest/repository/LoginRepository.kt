package com.dai1678.quest.repository

import android.util.Log
import com.dai1678.quest.entity.LoginResponse
import com.dai1678.quest.net.QuestApiClient

class LoginRepository : BaseRepository() {

    companion object Factory {
        private var instance: LoginRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: LoginRepository().also { instance = it }
        }
    }

    suspend fun login(username: String, password: String): LoginResponse? {
        return try {
            safeApiCall(
                call = QuestApiClient.loginApi.loginAsync(username, password),
                error = "Login Error!"
            )
        } catch (e: Exception) {
            Log.e("Error", e.toString())
            LoginResponse(
                auth = false,
                token = null,
                user = null
            )
        }
    }
}
