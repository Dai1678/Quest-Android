package com.dai1678.quest.ui.new_questionnaire

import android.util.Log
import androidx.lifecycle.ViewModel
import com.dai1678.quest.App
import com.dai1678.quest.R
import com.dai1678.quest.entity.QuestionnaireResult

class QuestionnaireAnswerViewModel : ViewModel() {
    private val resource = App.instance.resources

    val questionNumberLabels: Array<String> =
        resource.getStringArray(R.array.questionnaire_main_number_label_page_array)

    val questionMessages: Array<String> =
        resource.getStringArray(R.array.questionnaire_message_array)

    val answerChoice1Messages: Array<String> =
        resource.getStringArray(R.array.questionnaire_answer_1_array)

    val answerChoice2Messages: Array<String> =
        resource.getStringArray(R.array.questionnaire_answer_2_array)

    val answerChoice3Messages: Array<String> =
        resource.getStringArray(R.array.questionnaire_answer_3_array)

    val answerChoice4Messages: Array<String> =
        resource.getStringArray(R.array.questionnaire_answer_4_array)

    val answerChoice5Messages: Array<String> =
        resource.getStringArray(R.array.questionnaire_answer_5_array)

    private var questionnaireResult = QuestionnaireResult()

    fun setQuestionnaireResult(page: Int, answer: Int) {
        when (page) {
            1 -> questionnaireResult.page1Answer = answer
            2 -> questionnaireResult.page2Answer = answer
            7 -> questionnaireResult.page7Answer = answer
            8 -> questionnaireResult.page8Answer = answer
            9 -> questionnaireResult.page9Answer = answer
            12 -> questionnaireResult.page12Answer = answer
        }
    }

    fun setQuestionnaireResult(page: Int, index: Int, answer: Int) {
        when (page) {
            3 -> questionnaireResult.page3Answer[index] = answer
            4 -> questionnaireResult.page4Answer[index] = answer
            5 -> questionnaireResult.page5Answer[index] = answer
            6 -> questionnaireResult.page6Answer[index] = answer
            10 -> questionnaireResult.page10Answer[index] = answer
            11 -> questionnaireResult.page11Answer[index] = answer
            13 -> questionnaireResult.page13Answer[index] = answer
        }
    }

    fun sendQuestionnaireResult() {
        Log.d("answer", questionnaireResult.toString())
    }
}
