package com.dai1678.quest.net

import com.dai1678.quest.entity.SuccessResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST

interface HospitalApi {

    // 病院データへのログイン
    @POST("auth/login")
    fun loginAsync(
        @Field("hospitalNumber") hospitalNumber: Long,
        @Field("password") password: String
    ): Deferred<Response<SuccessResponse>>

    // 病院の患者データのフェッチ
    @GET("patients/fetch")
    fun fetchPatients()
}
