package com.dai1678.quest.net

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object QuestApiClient {
    // FIXME 環境によってサーバーの接続先を変更する
    private const val API_URL = "http://192.168.0.4:3000/api/v1/"

    val userApi: UserApi = create(UserApi::class.java)
    val questionnaireApi: QuestionnaireApi = create(QuestionnaireApi::class.java)

    private lateinit var retrofit: Retrofit

    private fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    private fun <S> create(serviceClass: Class<S>): S {
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
