package com.dai1678.quest.net

import com.dai1678.quest.entity.BaseResponse
import com.dai1678.quest.entity.Patient
import com.dai1678.quest.entity.Patient2
import com.dai1678.quest.entity.PatientListResponse
import retrofit2.Response
import retrofit2.http.*

interface PatientApi {

    @GET("patients")
    suspend fun getPatientListAsync(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<PatientListResponse>

    @POST("patients")
    suspend fun createPatientAsync(
        @Body patient: Patient2
    ): Response<BaseResponse>

    @GET("patient/{id}")
    suspend fun getPatientAsync(
        @Path("id") patientId: String
    ): Response<Patient>
}
