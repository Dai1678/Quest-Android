package com.dai1678.quest.net

import com.dai1678.quest.entity.BaseResponse
import com.dai1678.quest.entity.Patient
import com.dai1678.quest.entity.PatientListResponse
import retrofit2.Response
import retrofit2.http.*

interface PatientApi {

    @GET("patients")
    suspend fun getPatientListAsync(
        @Header("Authorization") authToken: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("hospitalId") hospitalId: String
    ): Response<PatientListResponse>

    @POST("patients")
    suspend fun createPatientAsync(
        @Header("Authorization") authToken: String,
        @Body patient: Patient
    ): Response<BaseResponse>

    @GET("patient/{id}")
    suspend fun getPatientAsync(
        @Header("Authorization") authToken: String,
        @Path("id") patientId: String
    ): Response<Patient>
}
