package com.dai1678.quest.entity

import java.util.UUID

data class Patient(
    val id: String = UUID.randomUUID().toString(),
    val firstName: String,
    val lastName: String,
    val firstNameReading: String,
    val lastNameReading: String,
    val gender: String,
    val ageRange: String,
    val questionnaires: List<Questionnaire> = listOf()
)

data class PatientListResponse(
    val total: Int,
    val list: List<Patient> = listOf()
)
