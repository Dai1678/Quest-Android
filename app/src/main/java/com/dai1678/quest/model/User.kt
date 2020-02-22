package com.dai1678.quest.model

import java.util.UUID

data class User(
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
    val list: List<User> = listOf()
)
