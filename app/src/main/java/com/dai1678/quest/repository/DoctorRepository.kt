package com.dai1678.quest.repository

import com.dai1678.quest.net.QuestApiClient

class DoctorRepository {

    suspend fun getDoctorList() =
        QuestApiClient.doctorApi.getDoctorListAsync()

    companion object {

        @Volatile
        private var instance: DoctorRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: DoctorRepository().also { instance = it }
        }
    }
}
