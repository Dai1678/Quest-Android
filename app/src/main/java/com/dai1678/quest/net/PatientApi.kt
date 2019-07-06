package com.dai1678.quest.net

import com.dai1678.quest.entity.BaseResponse
import com.dai1678.quest.entity.Patient
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface PatientApi {

    @POST("auth/register/patient")
    suspend fun registerPatientAsync(
        @Header("Authorization") authToken: String,
        @Body user: Patient
    ): Response<BaseResponse>
}
