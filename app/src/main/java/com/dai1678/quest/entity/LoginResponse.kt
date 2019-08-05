package com.dai1678.quest.entity

data class LoginResponse(
    val auth: Boolean,
    val token: String?,
    val doctor: Doctor?
)
