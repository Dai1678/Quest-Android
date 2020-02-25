package com.dai1678.quest.net

import com.dai1678.quest.model.Questionnaire
import com.dai1678.quest.model.QuestionnaireListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * 回答結果のAPIエンドポイントを定義したインターフェース
 */
interface QuestionnaireApi {

    /**
     * 回答結果の取得
     * @param userId 受検者のID
     * @return QuestionnaireListResponse
     */
    @GET("questionnaires")
    suspend fun getResultList(
        @Query("patientId") userId: String
    ): QuestionnaireListResponse

    /**
     * 回答結果の保存
     * @param questionnaire 回答結果
     * @return 回答結果
     */
    @POST("questionnaires")
    suspend fun createResult(
        @Body questionnaire: Questionnaire
    ): Questionnaire

    /**
     * 回答結果の取得
     * @param questionnaireId 回答結果のID
     * @return 回答結果
     */
    @GET("questionnaires/{id}")
    suspend fun getResult(
        @Path("id") questionnaireId: String
    ): Questionnaire
}
