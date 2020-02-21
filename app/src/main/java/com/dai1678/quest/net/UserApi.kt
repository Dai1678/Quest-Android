package com.dai1678.quest.net

import com.dai1678.quest.model.DefaultResponse
import com.dai1678.quest.model.Patient
import com.dai1678.quest.model.PatientListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {

    @GET("patients")
    suspend fun getUsers(): PatientListResponse

    @POST("patients")
    suspend fun createUser(
        @Body patient: Patient
    ): DefaultResponse

    @GET("patient/{id}")
    suspend fun getUser(
        @Path("id") userId: String
    ): Patient
}
