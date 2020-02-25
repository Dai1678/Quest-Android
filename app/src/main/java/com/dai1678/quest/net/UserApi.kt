package com.dai1678.quest.net

import com.dai1678.quest.model.DefaultResponse
import com.dai1678.quest.model.User
import com.dai1678.quest.model.PatientListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * 受検者情報のAPIエンドポイントを定義したインターフェース
 */
interface UserApi {
    /**
     * 受検者情報の取得
     * @return PatientListResponse
     */
    @GET("patients")
    suspend fun getUsers(): PatientListResponse

    /**
     * 受検者情報の保存
     * @param user 受検者情報
     * @return DefaultResponse
     */
    @POST("patients")
    suspend fun createUser(
        @Body user: User
    ): DefaultResponse

    /**
     * 受検者情報の取得
     * @param userId 受検者のid
     * @return 受検者情報
     */
    @GET("patient/{id}")
    suspend fun getUser(
        @Path("id") userId: String
    ): User
}
