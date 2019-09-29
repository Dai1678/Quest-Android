package com.dai1678.quest.repository

import com.dai1678.quest.entity.BaseResponse
import com.dai1678.quest.entity.Patient
import com.dai1678.quest.entity.Patient2
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
        page: Int,
        limit: Int
    ): PatientListResponse? {
        return safeApiCall(
            call = QuestApiClient.patientApi.getPatientListAsync(limit, page),
            error = "Get Patient Error!"
        )
    }

    suspend fun createPatient(patient: Patient2): BaseResponse? {
        return safeApiCall(
            call = QuestApiClient.patientApi.createPatientAsync(patient),
            error = "Register Error!"
        )
    }

    suspend fun getPatient(patientId: String): Patient? {
        return safeApiCall(
            call = QuestApiClient.patientApi.getPatientAsync(patientId),
            error = "Get Patient Error"
        )
    }
}
