package com.dai1678.quest.repository

import com.dai1678.quest.entity.SuccessResponse
import com.dai1678.quest.net.QuestApiClient.hospitalApi

class HospitalRepository : BaseRepository() {

    companion object Factory {
        private var instance: HospitalRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: HospitalRepository().also { instance = it }
        }
    }

    suspend fun login(hospitalNumber: Long, password: String): SuccessResponse? {
        return safeApiCall(
            call = { hospitalApi.loginAsync(hospitalNumber, password).await() },
            error = "Login Error!"
        )
    }

    fun fetch() {
        hospitalApi.fetchPatients()
    }
}
