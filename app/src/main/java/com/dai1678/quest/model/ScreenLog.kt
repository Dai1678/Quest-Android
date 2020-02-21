package com.dai1678.quest.model

data class ScreenLog(
    val patientId: String,
    val currentPage: Int,
    val currentTime: Long,
    val scrollCount: Int,
    val gender: String,
    val ageRange: String
)
