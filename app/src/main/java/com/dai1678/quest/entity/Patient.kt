package com.dai1678.quest.entity

data class Patient(
    val id: String,
    val firstName: String,
    val lastName: String,
    val firstNameReading: String,
    val lastNameReading: String,
    val updatedAt: String,
    val hospitalId: String,
    val questionnaires: List<Questionnaire> = listOf()
)

data class PatientListResponse(
    val total: Int,
    val list: List<Patient> = listOf()
)
