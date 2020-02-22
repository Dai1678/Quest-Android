package com.dai1678.quest.repository

import com.dai1678.quest.model.DefaultResponse
import com.dai1678.quest.model.User
import com.dai1678.quest.model.PatientListResponse
import com.dai1678.quest.net.NetworkResult
import com.dai1678.quest.net.QuestApiClient

/**
 * ユーザー情報 リポジトリ層
 */
class UserRepository {

    // ユーザーリストの取得
    suspend fun getUsers(): NetworkResult<PatientListResponse> {
        return try {
            val users = QuestApiClient.userApi.getUsers()
            NetworkResult.Success(users)
        } catch (e: Exception) {
            NetworkResult.Error(e)
        }
    }

    // ユーザー作成
    suspend fun createUser(user: User): NetworkResult<DefaultResponse> {
        return try {
            val response = QuestApiClient.userApi.createUser(user)
            NetworkResult.Success(response)
        } catch (e: Exception) {
            NetworkResult.Error(e)
        }
    }

    // Id指定によるユーザー情報の取得
    suspend fun getUser(userId: String): NetworkResult<User> {
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
