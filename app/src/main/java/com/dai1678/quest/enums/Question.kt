package com.dai1678.quest.enums

import android.content.Context
import androidx.annotation.StringRes
import com.dai1678.quest.R

enum class Question(
    val size: Int,
    @StringRes
    val titleResId: Int
) {
    PAGE0(0, R.string.questionnaire_toolbar_title),
    PAGE1(1, R.string.questionnaire_1_number),
    PAGE2(1, R.string.questionnaire_2_number),
    PAGE3(5, R.string.questionnaire_3_number),
    PAGE4(5, R.string.questionnaire_3_number),
    PAGE5(4, R.string.questionnaire_4_number),
    PAGE6(3, R.string.questionnaire_5_number),
    PAGE7(1, R.string.questionnaire_6_number),
    PAGE8(1, R.string.questionnaire_7_message),
    PAGE9(1, R.string.questionnaire_8_number),
    PAGE10(5, R.string.questionnaire_9_number),
    PAGE11(4, R.string.questionnaire_9_number),
    PAGE12(1, R.string.questionnaire_10_number),
    PAGE13(4, R.string.questionnaire_11_number),
    PAGE14(0, R.string.questionnaire_toolbar_title);

    fun getTitle(context: Context): String {
        return context.getString(titleResId)
    }

    companion object {
        fun valueOf(ordinal: Int): Question {
            return values().find { it.ordinal == ordinal } ?: PAGE0
        }
    }
}
