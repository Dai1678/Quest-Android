package com.dai1678.quest.net

import com.dai1678.quest.entity.Doctor
import com.dai1678.quest.entity.DoctorListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface DoctorApi {

    @GET("doctors")
    suspend fun getDoctorListAsync(): Response<DoctorListResponse>

    @POST("doctors")
    suspend fun createDoctorAsync(
        @Body doctor: Doctor
    ): Response<Doctor>

    @GET("doctor/{id}")
    suspend fun getDoctorAsync(
        @Path("id") doctorId: String
    ): Response<Doctor>

    // @PUT

    // @DELETE
}
