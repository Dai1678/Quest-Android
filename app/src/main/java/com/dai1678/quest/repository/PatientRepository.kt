package com.dai1678.quest.repository

import com.dai1678.quest.entity.Patient
import com.dai1678.quest.entity.PatientListResponse
import com.dai1678.quest.net.NetworkResult
import com.dai1678.quest.net.QuestApiClient
import java.lang.Exception

class PatientRepository {

    suspend fun getUsers() : NetworkResult<PatientListResponse> {
        return try {
            val users = QuestApiClient.patientApi.getUsers()
            NetworkResult.Success(users)
        } catch (e: Exception) {
            NetworkResult.Error(e)
        }
    }

    suspend fun createPatient(patient: Patient) =
        QuestApiClient.patientApi.createPatientAsync(patient)

    suspend fun getPatient(patientId: String) = QuestApiClient.patientApi.getPatientAsync(patientId)

    companion object Factory {
        private var instance: PatientRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: PatientRepository().also { instance = it }
        }
    }
}
