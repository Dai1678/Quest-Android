package com.dai1678.quest.repository

import android.util.Log
import com.dai1678.quest.net.Result
import retrofit2.Response
import java.io.IOException

open class BaseRepository {

    fun <T : Any> safeApiCall(call: Response<T>, error: String): T? {
        val result = apiOutput(call, error)
        var data: T? = null
        when (result) {
            is Result.Success ->
                data = result.data
            is Result.Error ->
                Log.e("ApiError", "$error & Exception - ${result.exception}")
        }
        return data
    }

    private fun <T : Any> apiOutput(response: Response<T>, error: String): Result<T> {
        return if (response.isSuccessful) {
            Result.Success(response.body()!!)
        } else {
            Result.Error(
                IOException(
                    "Error Occurred during getting api result.\n" +
                            "ErrorMessage : $error"
                )
            )
        }
    }
}
