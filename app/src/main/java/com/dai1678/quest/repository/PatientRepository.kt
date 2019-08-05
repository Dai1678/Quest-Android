package com.dai1678.quest.repository

import com.dai1678.quest.entity.BaseResponse
import com.dai1678.quest.entity.Patient
import com.dai1678.quest.entity.PatientListResponse
import com.dai1678.quest.net.QuestApiClient

class PatientRepository : BaseRepository() {

    companion object Factory {
        private var instance: PatientRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: PatientRepository().also { instance = it }
        }
    }

    suspend fun getPatientList(
        token: String,
        page: Int,
        limit: Int,
        hospitalId: String
    ): PatientListResponse? {
        return safeApiCall(
            call = QuestApiClient.patientApi.getPatientListAsync(token, limit, page, hospitalId),
            error = "Get Patient Error!"
        )
    }

    suspend fun createPatient(token: String, patient: Patient): BaseResponse? {
        return safeApiCall(
            call = QuestApiClient.patientApi.createPatientAsync(token, patient),
            error = "Register Error!"
        )
    }

    suspend fun getPatient(token: String, patientId: String): Patient? {
        return safeApiCall(
            call = QuestApiClient.patientApi.getPatientAsync(token, patientId),
            error = "Get Patient Error"
        )
    }
}
