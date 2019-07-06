package com.dai1678.quest.repository

import com.dai1678.quest.entity.BaseResponse
import com.dai1678.quest.entity.Patient
import com.dai1678.quest.net.QuestApiClient

class PatientRepository : BaseRepository() {

    companion object Factory {
        private var instance: PatientRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: PatientRepository().also { instance = it }
        }
    }

    suspend fun register(token: String, patient: Patient): BaseResponse? {
        return safeApiCall(
            call = QuestApiClient.patientApi.registerPatientAsync(token, patient),
            error = "Register Error!"
        )
    }
}
