package com.dai1678.quest.entity

data class BaseResponse(
    val message: String
)

data class LoginResponse(
    val auth: Boolean,
    val token: String?,
    val message: String
)
