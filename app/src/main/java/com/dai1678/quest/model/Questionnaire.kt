package com.dai1678.quest.model

import com.dai1678.quest.enums.DateFormat
import java.util.Date
import java.util.UUID

/**
 * 結果送信に使用するデータクラス
 * @param id UUIDで定義されるランダムな文字列
 * @param result 回答結果
 * @param createdAt 作成日時
 * @param updatedAt 更新日時
 * @param patientId ユーザーのid
 */
data class Questionnaire(
    val id: String = UUID.randomUUID().toString(),
    val result: QuestionnaireResult,
    val createdAt: String = DateFormat.YYYYMMDD_HHMMSS.format(Date()),
    val updatedAt: String = DateFormat.YYYYMMDD_HHMMSS.format(Date()),
    val patientId: String
)

/**
 * 回答画面で選択した回答結果のデータクラス
 * RadioButtonの上から順に1...と定義し、その数値を持つ
 *
 * @param page1Answer: 1ページ目(問1)で選択した回答項目の番号
 * @param page2Answer: 2ページ目(問2)で選択した回答項目の番号
 * @param page3Answer: 3ページ目(問3の前半5問)で選択した回答項目の番号
 * @param page4Answer: 4ページ目(問3の後半5問)で選択した回答項目の番号
 * ...
 */
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

/**
 * APIで回答結果を受け取った際のレスポンスのデータクラス
 * @param total 受け取った回答結果の数
 * @param list 回答結果
 */
data class QuestionnaireListResponse(
    val total: Int,
    val list: List<Questionnaire> = listOf()
)
