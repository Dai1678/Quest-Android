package com.dai1678.quest.enums

import android.content.Context
import androidx.annotation.ArrayRes
import com.dai1678.quest.R

// 何も指定したくないとき
private const val NONE = 0

/**
 * 回答ページごとの回答項目のデータをまとめたenum
 *
 * @param answerMessagesResId 回答項目文言の文字配列リソース
 */
enum class Answer(
    @ArrayRes
    val answerMessagesResId: Int
) {
    PAGE0(NONE),
    PAGE1(R.array.questionnaire_1_answers),
    PAGE2(R.array.questionnaire_2_answers),
    PAGE3(R.array.questionnaire_3_answers),
    PAGE4(R.array.questionnaire_3_answers),
    PAGE5(R.array.questionnaire_4_answers),
    PAGE6(R.array.questionnaire_5_answers),
    PAGE7(R.array.questionnaire_6_answers),
    PAGE8(R.array.questionnaire_7_answers),
    PAGE9(R.array.questionnaire_8_answers),
    PAGE10(R.array.questionnaire_9_answers),
    PAGE11(R.array.questionnaire_9_answers),
    PAGE12(R.array.questionnaire_10_answers),
    PAGE13(R.array.questionnaire_11_answers),
    PAGE14(NONE);

    /**
     * Answer enumを元に回答文言の文字配列を返す
     * @param context Context
     * @return 回答文言の文字配列
     */
    fun getAnswers(context: Context): Array<String> {
        return if (answerMessagesResId == NONE) {
            arrayOf(context.getString(R.string.blank))
        } else {
            context.resources.getStringArray(answerMessagesResId)
        }
    }

    companion object {
        fun valueOf(ordinal: Int): Answer {
            return values().find { it.ordinal == ordinal } ?: PAGE1
        }
    }
}
