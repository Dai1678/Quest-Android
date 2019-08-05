package com.dai1678.quest.net

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object QuestApiClient {
    private const val API_URL = "http://192.168.0.9:3000/api/v1/" // FIXME APIの接続先URLの定義

    val loginApi: LoginApi = create(LoginApi::class.java)
    val patientApi: PatientApi = create(PatientApi::class.java)
    val doctorApi: DoctorApi = create(DoctorApi::class.java)

    private lateinit var retrofit: Retrofit

    private fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    fun <S> create(serviceClass: Class<S>): S {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(getClient())
            .build()

        return retrofit.create(serviceClass)
    }
}
