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
        token: String,
        page: Int,
        limit: Int,
        patientId: String
    ): QuestionnaireListResponse? {
        return safeApiCall(
            call = QuestApiClient.questionnaireApi.getResultListAsync(token, page, limit, patientId),
            error = "Get Questionnaire Result List Error!"
        )
    }

    suspend fun createResult(token: String, questionnaire: Questionnaire): BaseResponse? {
        return safeApiCall(
            call = QuestApiClient.questionnaireApi.createResultAsync(token, questionnaire),
            error = "Create Questionnaire Error!"
        )
    }

    suspend fun getResult(token: String, questionnaireId: String): Questionnaire? {
        return safeApiCall(
            call = QuestApiClient.questionnaireApi.getResultAsync(token, questionnaireId),
            error = "Get Questionnaire Result Error!"
        )
    }

}
