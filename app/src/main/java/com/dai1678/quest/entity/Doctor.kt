package com.dai1678.quest.entity

data class Doctor(
    val id: String,
    val password: String,
    val isAdmin: Boolean,
    val hospitalId: String
)

data class DoctorListResponse(
    val total: Int,
    val list: List<Doctor?>
)
