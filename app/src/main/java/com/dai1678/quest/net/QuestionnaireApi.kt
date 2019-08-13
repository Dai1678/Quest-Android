package com.dai1678.quest.net

import com.dai1678.quest.entity.BaseResponse
import com.dai1678.quest.entity.Questionnaire
import com.dai1678.quest.entity.QuestionnaireListResponse
import retrofit2.Response
import retrofit2.http.*

interface QuestionnaireApi {

    @GET("questionnaires")
    suspend fun getResultListAsync(
        @Header("Authorization") authToken: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("patientId") patientId: String
    ): Response<QuestionnaireListResponse>

    @POST("questionnaires")
    suspend fun createResultAsync(
        @Header("Authorization") authToken: String,
        @Body questionnaire: Questionnaire
    ): Response<BaseResponse>

    @GET("questionnaires/{id}")
    suspend fun getResultAsync(
        @Header("Authorization") authToken: String,
        @Path("id") questionnaireId: String
    ): Response<Questionnaire>

}
