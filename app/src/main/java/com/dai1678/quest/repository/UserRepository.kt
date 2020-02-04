package com.dai1678.quest.repository

import com.dai1678.quest.entity.Patient
import com.dai1678.quest.entity.PatientListResponse
import com.dai1678.quest.net.NetworkResult
import com.dai1678.quest.net.QuestApiClient
import java.lang.Exception

/**
 * ユーザー情報 リポジトリ層
 */
class UserRepository {

    // ユーザーリストの取得
    suspend fun getUsers() : NetworkResult<PatientListResponse> {
        return try {
            val users = QuestApiClient.userApi.getUsers()
            NetworkResult.Success(users)
        } catch (e: Exception) {
            NetworkResult.Error(e)
        }
    }

    // ユーザー作成
    suspend fun createPatient(patient: Patient) =
        QuestApiClient.userApi.createPatientAsync(patient)

    // Id指定によるユーザー情報の取得
    suspend fun getUser(userId: String) : NetworkResult<Patient> {
        return try {
            val user = QuestApiClient.userApi.getUser(userId)
            NetworkResult.Success(user)
        } catch (e: Exception) {
            NetworkResult.Error(e)
        }
    }

    companion object Factory {
        private var instance: UserRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: UserRepository().also { instance = it }
        }
    }
}
