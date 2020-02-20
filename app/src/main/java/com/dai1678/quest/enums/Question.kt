package com.dai1678.quest.enums

import android.content.Context
import androidx.annotation.StringRes
import com.dai1678.quest.R

enum class Question(
    val size: Int,
    @StringRes
    val titleResId: Int,
    @StringRes
    val messageResId: Int
) {
    PAGE0(0, R.string.questionnaire_toolbar_title, R.string.blank),
    PAGE1(1, R.string.questionnaire_1_number, R.string.questionnaire_1_message),
    PAGE2(1, R.string.questionnaire_2_number, R.string.questionnaire_2_message),
    PAGE3(5, R.string.questionnaire_3_number, R.string.questionnaire_3_message),
    PAGE4(5, R.string.questionnaire_3_number, R.string.questionnaire_3_message),
    PAGE5(4, R.string.questionnaire_4_number, R.string.questionnaire_4_message),
    PAGE6(3, R.string.questionnaire_5_number, R.string.questionnaire_5_message),
    PAGE7(1, R.string.questionnaire_6_number, R.string.questionnaire_6_message),
    PAGE8(1, R.string.questionnaire_7_number, R.string.questionnaire_7_message),
    PAGE9(1, R.string.questionnaire_8_number, R.string.questionnaire_8_message),
    PAGE10(5, R.string.questionnaire_9_number, R.string.questionnaire_9_message),
    PAGE11(4, R.string.questionnaire_9_number, R.string.questionnaire_9_message),
    PAGE12(1, R.string.questionnaire_10_number, R.string.questionnaire_10_message),
    PAGE13(4, R.string.questionnaire_11_number, R.string.questionnaire_11_message),
    PAGE14(0, R.string.questionnaire_toolbar_title, R.string.blank);

    fun getTitle(context: Context): String = context.getString(titleResId)

    fun getMessage(context: Context): String = context.getString(messageResId)

    companion object {
        fun valueOf(ordinal: Int): Question {
            return values().find { it.ordinal == ordinal } ?: PAGE0
        }
    }
}
