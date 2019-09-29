package com.dai1678.quest.entity

data class Doctor(
    val id: String,
    val password: String,
    val isAdmin: Boolean,
    val hospitalId: String
)

data class DoctorListResponse(
    val total: Int,
    val list: List<Doctor> = listOf()
)

data class Doctor2(
    val id: String,
    val firstName: String,
    val lastName: String,
    val updatedAt: String,
    val isAdmin: Boolean
)
