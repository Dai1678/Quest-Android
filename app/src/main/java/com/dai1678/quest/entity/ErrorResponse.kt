package com.dai1678.quest.entity

data class ErrorResponse(
    val statusCode: Int,
    val error: String,
    val message: String
)
