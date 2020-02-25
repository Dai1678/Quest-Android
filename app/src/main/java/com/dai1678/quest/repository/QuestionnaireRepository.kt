package com.dai1678.quest.repository

import com.dai1678.quest.model.Questionnaire
import com.dai1678.quest.model.QuestionnaireListResponse
import com.dai1678.quest.net.NetworkResult
import com.dai1678.quest.net.QuestApiClient

/**
 * 回答結果に関するリポジトリモジュール
 */
class QuestionnaireRepository {
    /**
     * 回答結果の取得
     * @param userId 受検者のID
     * @return QuestionnaireListResponse
     * @throws Exception
     */
    suspend fun getResultList(userId: String): NetworkResult<QuestionnaireListResponse> {
        return try {
            val response = QuestApiClient.questionnaireApi.getResultList(userId)
            NetworkResult.Success(response)
        } catch (e: Exception) {
            NetworkResult.Error(e)
        }
    }

    /**
     * 回答結果の保存
     * @param questionnaire 回答結果
     * @return 回答結果
     * @throws Exception
     */
    suspend fun createResult(questionnaire: Questionnaire): NetworkResult<Questionnaire> {
        return try {
            val response = QuestApiClient.questionnaireApi.createResult(questionnaire)
            NetworkResult.Success(response)
        } catch (e: Exception) {
            NetworkResult.Error(e)
        }
    }

    /**
     * 回答結果の取得
     * @param questionnaireId 回答結果のID
     * @return 回答結果
     * @throws Exception
     */
    suspend fun getResult(questionnaireId: String): NetworkResult<Questionnaire> {
        return try {
            val response = QuestApiClient.questionnaireApi.getResult(questionnaireId)
            NetworkResult.Success(response)
        } catch (e: Exception) {
            NetworkResult.Error(e)
        }
    }

    companion object Factory {
        private var instance: QuestionnaireRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: QuestionnaireRepository().also { instance = it }
        }
    }
}
