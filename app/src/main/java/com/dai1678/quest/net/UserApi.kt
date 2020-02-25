package com.dai1678.quest.net

import com.dai1678.quest.model.DefaultResponse
import com.dai1678.quest.model.User
import com.dai1678.quest.model.PatientListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * ユーザー情報のAPIエンドポイントを定義したインターフェース
 */
interface UserApi {
    /**
     * ユーザー情報の取得
     * @return PatientListResponse
     */
    @GET("patients")
    suspend fun getUsers(): PatientListResponse

    /**
     * ユーザー情報の保存
     * @param user ユーザー情報
     * @return DefaultResponse
     */
    @POST("patients")
    suspend fun createUser(
        @Body user: User
    ): DefaultResponse

    /**
     * ユーザー情報の取得
     * @param userId ユーザーのid
     * @return ユーザー情報
     */
    @GET("patient/{id}")
    suspend fun getUser(
        @Path("id") userId: String
    ): User
}
