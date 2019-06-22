package com.dai1678.quest.net

import com.dai1678.quest.entity.BaseResponse
import com.dai1678.quest.entity.Doctor
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface DoctorApi {

    @POST("auth/register/doctor")
    suspend fun registerDoctorAsync(
        @Header("Authorization") Authorization: String,
        @Body user: Doctor
    ): Response<BaseResponse>
}
