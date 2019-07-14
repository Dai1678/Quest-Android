package com.dai1678.quest.entity

data class Patient(
    val username: String,
    val firstName: String,
    val lastName: String,
    val questionnaireId: String?,
    val updatedAt: String,
    val hospitalId: String
)

data class PatientListResponse(
    val total: Int,
    val list: List<Patient>
)
