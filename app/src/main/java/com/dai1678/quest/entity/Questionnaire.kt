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
    var answer1: String?,
    var answer2: String?,
    var answer3: List<String>?,
    var answer4: List<String>?,
    var answer5: List<String>?,
    var answer6: String?,
    var answer7: String?,
    var answer8: String?,
    var answer9: List<String>?,
    var answer10: String?,
    var answer11: List<String>?
)

data class QuestionnaireListResponse(
    val total: Int,
    val list: List<Questionnaire?>
)
