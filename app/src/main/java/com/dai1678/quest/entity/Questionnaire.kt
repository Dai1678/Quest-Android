package com.dai1678.quest.entity

data class Questionnaire(
    val id: String,
    val result: QuestionnaireResult,
    val responsibleDoctorId: String,
    val createdAt: String,
    val updatedAt: String,
    val patientId: String
)

data class QuestionnaireResult(
    val answer1: String,
    val answer2: String,
    val answer3: String,
    val answer4: String,
    val answer5: String,
    val answer6: String,
    val answer7: String,
    val answer8: String,
    val answer9: String,
    val answer10: String,
    val answer11: String
)
