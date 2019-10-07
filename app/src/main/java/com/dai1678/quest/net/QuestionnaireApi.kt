package com.dai1678.quest.net

import com.dai1678.quest.entity.Questionnaire
import com.dai1678.quest.entity.QuestionnaireListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface QuestionnaireApi {

    @GET("questionnaires")
    suspend fun getResultListAsync(
        @Query("patientId") patientId: String
    ): Response<QuestionnaireListResponse>

    @POST("questionnaires")
    suspend fun createResultAsync(
        @Body questionnaire: Questionnaire
    ): Response<Questionnaire>

    @GET("questionnaires/{id}")
    suspend fun getResultAsync(
        @Path("id") questionnaireId: String
    ): Response<Questionnaire>
}
