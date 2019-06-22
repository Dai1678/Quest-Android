package com.dai1678.quest.repository

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
        return safeApiCall(
            call = QuestApiClient.loginApi.loginAsync(username, password),
            error = "Login Error!"
        )
    }
}
