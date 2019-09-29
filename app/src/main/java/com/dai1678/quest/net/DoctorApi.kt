package com.dai1678.quest.net

import com.dai1678.quest.entity.BaseResponse
import com.dai1678.quest.entity.Doctor
import com.dai1678.quest.entity.DoctorListResponse
import retrofit2.Response
import retrofit2.http.*

interface DoctorApi {

    @GET("doctors")
    suspend fun getDoctorListAsync(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<DoctorListResponse>

    @POST("doctors")
    suspend fun createDoctorAsync(
        @Body doctor: Doctor
    ): Response<BaseResponse>

    @GET("doctor/{id}")
    suspend fun getDoctorAsync(
        @Path("id") doctorId: String
    ): Response<Doctor>

    // @PUT

    // @DELETE
}
