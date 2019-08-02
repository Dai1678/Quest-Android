package com.dai1678.quest.net

import com.dai1678.quest.entity.LoginResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginApi {

    // 病院データへのログイン
    @FormUrlEncoded
    @POST("auth/login")
    suspend fun loginAsync(
        @Field("id") username: String,
        @Field("password") password: String
    ): Response<LoginResponse>
}
