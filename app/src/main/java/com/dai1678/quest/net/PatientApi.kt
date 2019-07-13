package com.dai1678.quest.net

import com.dai1678.quest.entity.BaseResponse
import com.dai1678.quest.entity.Patient
import com.dai1678.quest.entity.PatientListResponse
import retrofit2.Response
import retrofit2.http.*

interface PatientApi {

    @POST("auth/register/patient")
    suspend fun registerPatientAsync(
        @Header("Authorization") authToken: String,
        @Body user: Patient
    ): Response<BaseResponse>

    @GET("users/patient/list")
    suspend fun getPatientListAsync(
        @Header("Authorization") authToken: String,
        @Query("hospitalId") hospitalId: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<PatientListResponse>

    @GET("users/patient")
    suspend fun getPatientAsync(
        @Header("Authorization") authToken: String,
        @Query("username") userName: String
    ): Response<Patient>
}
