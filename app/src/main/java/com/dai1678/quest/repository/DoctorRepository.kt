package com.dai1678.quest.repository

import com.dai1678.quest.entity.BaseResponse
import com.dai1678.quest.entity.Doctor
import com.dai1678.quest.net.QuestApiClient

class DoctorRepository : BaseRepository() {

    companion object Factory {
        private var instance: DoctorRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: DoctorRepository().also { instance = it }
        }
    }

    suspend fun register(token: String, doctor: Doctor): BaseResponse? {
        return safeApiCall(
            call = QuestApiClient.doctorApi.registerDoctorAsync(token, doctor),
            error = "Register Error!"
        )
    }
}
