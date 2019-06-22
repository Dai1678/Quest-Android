package com.dai1678.quest.repository

import android.util.Log
import com.dai1678.quest.net.Result
import retrofit2.Response
import java.io.IOException

open class BaseRepository {

    suspend fun <T : Any> safeApiCall(
        call: suspend () -> Response<T>, error: String
    ): T? {
        val result = apiOutput(call, error)
        var data: T? = null
        when (result) {
            is Result.Success ->
                data = result.data
            is Result.Error ->
                Log.d("ApiError", "$error & Exception - ${result.exception}")
        }
        return data
    }

    private suspend fun <T : Any> apiOutput(
        call: suspend () -> Response<T>, error: String
    ): Result<T> {
        val response = call.invoke()
        return if (response.isSuccessful) {
            Result.Success(response.body()!!)
        } else {
            Result.Error(
                IOException(
                    "Error Occurred during getting api result.\n" +
                            "Error message is $error"
                )
            )
        }
    }
}
