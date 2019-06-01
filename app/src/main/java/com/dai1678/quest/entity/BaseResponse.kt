package com.dai1678.quest.entity

data class BaseResponse<T>(
    val body: T?,
    val hasError: Boolean = false,
    val error: ErrorResponse? = null
)

data class ErrorResponse(
    val code: Int,
    val name: String,
    val message: String
)

data class SuccessResponse(
    val result: Boolean
)
