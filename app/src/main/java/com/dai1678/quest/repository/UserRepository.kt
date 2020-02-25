package com.dai1678.quest.repository

import com.dai1678.quest.model.DefaultResponse
import com.dai1678.quest.model.User
import com.dai1678.quest.model.PatientListResponse
import com.dai1678.quest.net.NetworkResult
import com.dai1678.quest.net.QuestApiClient

/**
 * ユーザー情報に関するリポジトリモジュール
 */
class UserRepository {
    /**
     * ユーザー情報の取得
     * @return PatientListResponse
     * @throws Exception
     */
    suspend fun getUsers(): NetworkResult<PatientListResponse> {
        return try {
            val users = QuestApiClient.userApi.getUsers()
            NetworkResult.Success(users)
        } catch (e: Exception) {
            NetworkResult.Error(e)
        }
    }

    /**
     * ユーザー情報の保存
     * @param user ユーザー情報
     * @return DefaultResponse
     * @throws Exception
     */
    suspend fun createUser(user: User): NetworkResult<DefaultResponse> {
        return try {
            val response = QuestApiClient.userApi.createUser(user)
            NetworkResult.Success(response)
        } catch (e: Exception) {
            NetworkResult.Error(e)
        }
    }

    /**
     * ユーザー情報の取得
     * @param userId ユーザーのid
     * @return ユーザー情報
     * @throws Exception
     */
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
