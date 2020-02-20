package com.dai1678.quest.repository

import com.dai1678.quest.entity.Questionnaire
import com.dai1678.quest.net.NetworkResult
import com.dai1678.quest.net.QuestApiClient

class QuestionnaireRepository {

    suspend fun getResultList(patientId: String) =
        QuestApiClient.questionnaireApi.getResultListAsync(patientId)

    suspend fun createResult(questionnaire: Questionnaire): NetworkResult<Questionnaire> {
        return try {
            val response = QuestApiClient.questionnaireApi.createResult(questionnaire)
            NetworkResult.Success(response)
        } catch (e: Exception) {
            NetworkResult.Error(e)
        }
    }

    suspend fun getResult(questionnaireId: String) =
        QuestApiClient.questionnaireApi.getResultAsync(questionnaireId)

    companion object Factory {
        private var instance: QuestionnaireRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: QuestionnaireRepository().also { instance = it }
        }
    }
}
