package com.dai1678.quest.entity

import androidx.annotation.StringRes
import com.dai1678.quest.R

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

data class QuestionChild(
    val questionChildNumberMessage: String?,
    val questionChildMessage: String?,
    val selectAnswerMessage: List<String> = listOf()
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

enum class Question1(@StringRes val resId: Int) {
    MAIN(R.string.questionnaire_1_message),
    ANSWERS(R.array.questionnaire_1_answers)
}

enum class Question2(@StringRes val resId: Int) {
    MAIN(R.string.questionnaire_2_message),
    ANSWERS(R.array.questionnaire_2_answers)
}

enum class Question3(@StringRes val resId: Int) {
    MAIN(R.string.questionnaire_3_message),
    SUB1(R.array.questionnaire_3_1_sub_messages),
    SUB2(R.array.questionnaire_3_2_sub_messages),
    ANSWERS(R.array.questionnaire_3_answers)
}

enum class Question4(@StringRes val resId: Int) {
    MAIN(R.string.questionnaire_4_message),
    SUB(R.array.questionnaire_4_sub_messages),
    ANSWERS(R.array.questionnaire_4_answers)
}

enum class Question5(@StringRes val resId: Int) {
    MAIN(R.string.questionnaire_5_message),
    SUB(R.array.questionnaire_5_sub_messages),
    ANSWERS(R.array.questionnaire_5_answers)
}

enum class Question6(@StringRes val resId: Int) {
    MAIN(R.string.questionnaire_6_message),
    ANSWERS(R.array.questionnaire_6_answers)
}

enum class Question7(@StringRes val resId: Int) {
    MAIN(R.string.questionnaire_7_message),
    ANSWERS(R.array.questionnaire_7_answers)
}

enum class Question8(@StringRes val resId: Int) {
    MAIN(R.string.questionnaire_8_message),
    ANSWERS(R.array.questionnaire_8_answers)
}

enum class Question9(@StringRes val resId: Int) {
    MAIN(R.string.questionnaire_9_message),
    SUB1(R.array.questionnaire_9_1_sub_messages),
    SUB2(R.array.questionnaire_9_2_sub_messages),
    ANSWERS(R.array.questionnaire_9_answers)
}

enum class Question10(@StringRes val resId: Int) {
    MAIN(R.string.questionnaire_10_message),
    ANSWERS(R.array.questionnaire_10_answers)
}

enum class Question11(@StringRes val resId: Int) {
    MAIN(R.string.questionnaire_11_message),
    SUB(R.array.questionnaire_11_sub_messages),
    ANSWERS(R.array.questionnaire_11_answers)
}
