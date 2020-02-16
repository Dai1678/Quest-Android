package com.dai1678.quest.net

import java.lang.Exception

/**
 * APIからのレスポンスの成功可否を管理する
 */
sealed class NetworkResult<out T : Any> {
    data class Success<out T : Any>(val value: T) : NetworkResult<T>()
    data class Error(val exception: Exception) : NetworkResult<Nothing>()
}
