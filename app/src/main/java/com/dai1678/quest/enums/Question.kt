package com.dai1678.quest.enums

import android.content.Context
import androidx.annotation.ArrayRes
import androidx.annotation.StringRes
import com.dai1678.quest.R

// 何も指定したくないとき
private const val NONE = 0

/**
 * 設問ごとに固定のリソースをまとめたenum
 *
 * @param size ページあたりの質問の数
 * @param titleResId 設問番号の文字列リソース
 * @param messageResId 設問文章の文字列リソース
 * @param subTitleNumberResId 小問番号の文字列リソース
 * @param subMessageResId 小問文章の文字列リソース
 */
enum class Question(
    val size: Int,
    @StringRes
    val titleResId: Int,
    @StringRes
    val messageResId: Int,
    @ArrayRes
    val subTitleNumberResId: Int,
    @ArrayRes
    val subMessageResId: Int
) {
    PAGE0(0, R.string.questionnaire_toolbar_title, R.string.blank, NONE, NONE),
    PAGE1(1, R.string.questionnaire_1_number, R.string.questionnaire_1_message, NONE, NONE),
    PAGE2(1, R.string.questionnaire_2_number, R.string.questionnaire_2_message, NONE, NONE),
    PAGE3(
        5,
        R.string.questionnaire_3_number,
        R.string.questionnaire_3_message,
        R.array.questionnaire_sub_first_half_numbers,
        R.array.questionnaire_3_1_sub_messages
    ),
    PAGE4(
        5,
        R.string.questionnaire_3_number,
        R.string.questionnaire_3_message,
        R.array.questionnaire_sub_latter_half_numbers,
        R.array.questionnaire_3_2_sub_messages
    ),
    PAGE5(
        4,
        R.string.questionnaire_4_number,
        R.string.questionnaire_4_message,
        R.array.questionnaire_sub_first_half_numbers,
        R.array.questionnaire_4_sub_messages
    ),
    PAGE6(
        3,
        R.string.questionnaire_5_number,
        R.string.questionnaire_5_message,
        R.array.questionnaire_sub_first_half_numbers,
        R.array.questionnaire_5_sub_messages
    ),
    PAGE7(1, R.string.questionnaire_6_number, R.string.questionnaire_6_message, NONE, NONE),
    PAGE8(1, R.string.questionnaire_7_number, R.string.questionnaire_7_message, NONE, NONE),
    PAGE9(1, R.string.questionnaire_8_number, R.string.questionnaire_8_message, NONE, NONE),
    PAGE10(
        5,
        R.string.questionnaire_9_number,
        R.string.questionnaire_9_message,
        R.array.questionnaire_sub_first_half_numbers,
        R.array.questionnaire_9_1_sub_messages
    ),
    PAGE11(
        4,
        R.string.questionnaire_9_number,
        R.string.questionnaire_9_message,
        R.array.questionnaire_sub_latter_half_numbers,
        R.array.questionnaire_9_2_sub_messages
    ),
    PAGE12(1, R.string.questionnaire_10_number, R.string.questionnaire_10_message, NONE, NONE),
    PAGE13(
        4,
        R.string.questionnaire_11_number,
        R.string.questionnaire_11_message,
        R.array.questionnaire_sub_first_half_numbers,
        R.array.questionnaire_11_sub_messages
    ),
    PAGE14(0, R.string.questionnaire_toolbar_title, R.string.blank, NONE, NONE);

    /**
     * Question enumを元に設問番号の文字列を返す
     * @param context Context
     * @return 設問番号 例: 問1
     */
    fun getTitle(context: Context): String = context.getString(titleResId)

    /**
     * Question enumを元に設問文章の文字列を返す
     * @param context Context
     * @return 設問文章
     */
    fun getMessage(context: Context): String = context.getString(messageResId)

    /**
     * Question enumを元に小問番号の文字配列を返す
     * @param context Context
     * @return 小問番号の配列 例: {"ア)", "イ)"...}
     */
    fun getSubTitleNumbers(context: Context): Array<String> {
        return if (subTitleNumberResId == NONE) {
            arrayOf(context.getString(R.string.blank))
        } else {
            context.resources.getStringArray(subTitleNumberResId)
        }
    }

    /**
     * Question enumを元に小問文章の文字配列を返す
     * @param context Context
     * @return 小問文章の配列
     */
    fun getSubMessages(context: Context): Array<String> {
        return if (subMessageResId == NONE) {
            arrayOf(context.getString(R.string.blank))
        } else {
            context.resources.getStringArray(subMessageResId)
        }
    }

    companion object {
        fun valueOf(ordinal: Int): Question {
            return values().find { it.ordinal == ordinal } ?: PAGE0
        }
    }
}
