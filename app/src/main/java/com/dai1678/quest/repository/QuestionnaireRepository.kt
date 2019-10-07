package com.dai1678.quest.repository

import com.dai1678.quest.entity.Questionnaire
import com.dai1678.quest.net.QuestApiClient

class QuestionnaireRepository {

    suspend fun getResultList(patientId: String) =
        QuestApiClient.questionnaireApi.getResultListAsync(patientId)

    suspend fun createResult(questionnaire: Questionnaire) =
        QuestApiClient.questionnaireApi.createResultAsync(questionnaire)

    suspend fun getResult(questionnaireId: String) =
        QuestApiClient.questionnaireApi.getResultAsync(questionnaireId)

    companion object Factory {
        private var instance: QuestionnaireRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: QuestionnaireRepository().also { instance = it }
        }
    }
}
