package com.dai1678.quest.repository

import com.dai1678.quest.entity.BaseResponse
import com.dai1678.quest.entity.Questionnaire
import com.dai1678.quest.entity.QuestionnaireListResponse
import com.dai1678.quest.net.QuestApiClient

class QuestionnaireRepository : BaseRepository() {

    companion object Factory {
        private var instance: QuestionnaireRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: QuestionnaireRepository().also { instance = it }
        }
    }

    suspend fun getResultList(
        page: Int,
        limit: Int,
        patientId: String
    ): QuestionnaireListResponse? {
        return safeApiCall(
            call = QuestApiClient.questionnaireApi.getResultListAsync(
                page, limit, patientId
            ),
            error = "Get Questionnaire Result List Error!"
        )
    }

    suspend fun createResult(questionnaire: Questionnaire): BaseResponse? {
        return safeApiCall(
            call = QuestApiClient.questionnaireApi.createResultAsync(questionnaire),
            error = "Create Questionnaire Error!"
        )
    }

    suspend fun getResult(questionnaireId: String): Questionnaire? {
        return safeApiCall(
            call = QuestApiClient.questionnaireApi.getResultAsync(questionnaireId),
            error = "Get Questionnaire Result Error!"
        )
    }
}
