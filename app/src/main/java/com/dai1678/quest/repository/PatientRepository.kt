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

    suspend fun getUser(userId: String) : NetworkResult<Patient> {
        return try {
            val user = QuestApiClient.patientApi.getUser(userId)
            NetworkResult.Success(user)
        } catch (e: Exception) {
            NetworkResult.Error(e)
        }
    }

    companion object Factory {
        private var instance: PatientRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: PatientRepository().also { instance = it }
        }
    }
}
