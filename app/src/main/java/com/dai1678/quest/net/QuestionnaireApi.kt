package com.dai1678.quest.net

import com.dai1678.quest.entity.BaseResponse
import com.dai1678.quest.entity.Questionnaire
import com.dai1678.quest.entity.QuestionnaireListResponse
import retrofit2.Response
import retrofit2.http.*

interface QuestionnaireApi {

    @GET("questionnaires")
    suspend fun getResultListAsync(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("patientId") patientId: String
    ): Response<QuestionnaireListResponse>

    @POST("questionnaires")
    suspend fun createResultAsync(
        @Body questionnaire: Questionnaire
    ): Response<BaseResponse>

    @GET("questionnaires/{id}")
    suspend fun getResultAsync(
        @Path("id") questionnaireId: String
    ): Response<Questionnaire>
}
