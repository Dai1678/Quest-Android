package com.dai1678.quest.entity

data class Questionnaire(
    val id: String,
    val result: QuestionnaireResult,
    val createdAt: String,
    val updatedAt: String,
    val patientId: String
)

data class QuestionnaireResult(
    var page1Answer: Int = 1,
    var page2Answer: Int = 1,
    var page3Answer: Array<Int> = arrayOf(1, 1, 1, 1, 1),
    var page4Answer: Array<Int> = arrayOf(1, 1, 1, 1, 1),
    var page5Answer: Array<Int> = arrayOf(1, 1, 1, 1),
    var page6Answer: Array<Int> = arrayOf(1, 1, 1),
    var page7Answer: Int = 1,
    var page8Answer: Int = 1,
    var page9Answer: Int = 1,
    var page10Answer: Array<Int> = arrayOf(1, 1, 1, 1, 1),
    var page11Answer: Array<Int> = arrayOf(1, 1, 1, 1),
    var page12Answer: Int = 1,
    var page13Answer: Array<Int> = arrayOf(1, 1, 1, 1)
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as QuestionnaireResult

        if (!page3Answer.contentEquals(other.page3Answer)) return false
        if (!page4Answer.contentEquals(other.page4Answer)) return false
        if (!page5Answer.contentEquals(other.page5Answer)) return false
        if (!page6Answer.contentEquals(other.page6Answer)) return false
        if (!page10Answer.contentEquals(other.page10Answer)) return false
        if (!page11Answer.contentEquals(other.page11Answer)) return false
        if (!page13Answer.contentEquals(other.page13Answer)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = page3Answer.contentHashCode()
        result = 31 * result + page4Answer.contentHashCode()
        result = 31 * result + page5Answer.contentHashCode()
        result = 31 * result + page6Answer.contentHashCode()
        result = 31 * result + page10Answer.contentHashCode()
        result = 31 * result + page11Answer.contentHashCode()
        result = 31 * result + page13Answer.contentHashCode()
        return result
    }
}

data class QuestionnaireListResponse(
    val total: Int,
    val list: List<Questionnaire> = listOf()
)

enum class QuestionSize(val size: Int) {
    PAGE0(0),
    PAGE1(1),
    PAGE2(1),
    PAGE3(5),
    PAGE4(5),
    PAGE5(4),
    PAGE6(3),
    PAGE7(1),
    PAGE8(1),
    PAGE9(1),
    PAGE10(5),
    PAGE11(4),
    PAGE12(1),
    PAGE13(4),
    PAGE14(0)
}
