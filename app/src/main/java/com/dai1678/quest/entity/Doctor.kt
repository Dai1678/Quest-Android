package com.dai1678.quest.entity

data class DoctorListResponse(
    val total: Int,
    val list: List<Doctor> = listOf()
)

data class Doctor(
    val id: String,
    val firstName: String,
    val lastName: String,
    val firstNameReading: String,
    val lastNameReading: String,
    val updatedAt: String,
    val isAdmin: Boolean,
    val hospitalId: String
)
