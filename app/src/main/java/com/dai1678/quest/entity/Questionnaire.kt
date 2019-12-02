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
    val answer1: String?,
    val answer2: String?,
    val answer3: List<String?> = listOf(),
    val answer4: List<String?> = listOf(),
    val answer5: List<String?> = listOf(),
    val answer6: String?,
    val answer7: String?,
    val answer8: String?,
    val answer9: List<String?> = listOf(),
    val answer10: String?,
    val answer11: List<String?> = listOf()
)

data class QuestionnaireListResponse(
    val total: Int,
    val list: List<Questionnaire> = listOf()
)

enum class QuestionSize(val pageNumber: Int, val size: Int) {
    PAGE0(0, 0),
    PAGE1(1, 1),
    PAGE2(2, 1),
    PAGE3(3, 5),
    PAGE4(4, 5),
    PAGE5(5, 4),
    PAGE6(6, 3),
    PAGE7(7, 1),
    PAGE8(8, 1),
    PAGE9(9, 1),
    PAGE10(10, 5),
    PAGE11(11, 4),
    PAGE12(12, 1),
    PAGE13(13, 4),
    PAGE14(14, 0)
}
