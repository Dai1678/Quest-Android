package com.dai1678.quest.enums

import android.content.Context
import androidx.annotation.ArrayRes
import com.dai1678.quest.R

private const val NONE = 0

enum class Answer(
    @ArrayRes
    val messages: Int
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

    fun getAnswers(context: Context): Array<String> {
        return if (messages == NONE) {
            arrayOf(context.getString(R.string.blank))
        } else {
            context.resources.getStringArray(messages)
        }
    }

    companion object {
        fun valueOf(ordinal: Int): Answer {
            return values().find { it.ordinal == ordinal } ?: PAGE1
        }
    }
}
