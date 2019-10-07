package com.dai1678.quest.repository

import com.dai1678.quest.entity.Patient
import com.dai1678.quest.net.QuestApiClient

class PatientRepository {

    suspend fun getPatientList() = QuestApiClient.patientApi.getPatientListAsync()

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
