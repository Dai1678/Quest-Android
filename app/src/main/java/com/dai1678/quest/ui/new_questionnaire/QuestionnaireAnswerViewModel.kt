package com.dai1678.quest.ui.new_questionnaire

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.dai1678.quest.App
import com.dai1678.quest.R
import com.dai1678.quest.entity.QuestionSize

class QuestionnaireAnswerViewModel : ViewModel() {

    private val resource = App.instance.resources

    var questionSize = 0

    private val mutableQuestionNumberLabel = MutableLiveData<String>()
    val questionNumberLabel: LiveData<String> = mutableQuestionNumberLabel

    private val mutableQuestionChildNumberLabels = MutableLiveData<List<String>>()
    val questionChildNumberLabels: LiveData<List<String>> = mutableQuestionChildNumberLabels

    private val mutableQuestionMessage = MutableLiveData<String>()
    val questionMessage: LiveData<String> = mutableQuestionMessage

    private val mutableQuestionChildMessages = MutableLiveData<List<String>>()
    val questionChildMessages: LiveData<List<String>> = mutableQuestionChildMessages

    // 3択と5択と6択しかない
    private val mutableAnswerChoiceMessages = MutableLiveData<List<String>>()
    val answerChoiceMessage1: LiveData<String> =
        Transformations.map(mutableAnswerChoiceMessages) { getAnswerChoiceMessage(it, 1) }
    val answerChoiceMessage2: LiveData<String> =
        Transformations.map(mutableAnswerChoiceMessages) { getAnswerChoiceMessage(it, 2) }
    val answerChoiceMessage3: LiveData<String> =
        Transformations.map(mutableAnswerChoiceMessages) { getAnswerChoiceMessage(it, 3) }
    val answerChoiceMessage4: LiveData<String> =
        Transformations.map(mutableAnswerChoiceMessages) { getAnswerChoiceMessage(it, 4) }
    val answerChoiceMessage5: LiveData<String> =
        Transformations.map(mutableAnswerChoiceMessages) { getAnswerChoiceMessage(it, 5) }
    val answerChoiceMessage6: LiveData<String> =
        Transformations.map(mutableAnswerChoiceMessages) { getAnswerChoiceMessage(it, 6) }

    // 表示しなくていい選択肢のViewのVisibleを管理
    private val mutableAnswerChoiceMessageVisible = MutableLiveData<Boolean>()
    val answerChoiceMessageVisible: LiveData<Boolean> = mutableAnswerChoiceMessageVisible

    private fun getAnswerChoiceMessage(messages: List<String>, answerNumber: Int): String {
        return if (messages.size >= answerNumber) {
            messages[answerNumber - 1]
        } else {
            ""
        }
    }

    fun setQuestionInfo(page: Int) {
        questionSize = QuestionSize.values()[page].size
        setQuestionNumberLabel(page)
        setQuestionMessage(page)
        setAnswerChoiceMessages(page)
        setAnswerChoiceMessageVisible(page)

        when (page) {
            3, 4, 5, 6, 10, 11, 13 -> {
                setQuestionChildLabels(page)
                setQuestionChildMessages(page)
            }
        }
    }

    private fun setQuestionNumberLabel(page: Int) {
        val label: String = when (page) {
            1 -> resource.getString(R.string.questionnaire_1_number)
            2 -> resource.getString(R.string.questionnaire_2_number)
            3 -> resource.getString(R.string.questionnaire_3_number)
            4 -> resource.getString(R.string.questionnaire_3_number)
            5 -> resource.getString(R.string.questionnaire_4_number)
            6 -> resource.getString(R.string.questionnaire_5_number)
            7 -> resource.getString(R.string.questionnaire_6_number)
            8 -> resource.getString(R.string.questionnaire_7_number)
            9 -> resource.getString(R.string.questionnaire_8_number)
            10 -> resource.getString(R.string.questionnaire_9_number)
            11 -> resource.getString(R.string.questionnaire_9_number)
            12 -> resource.getString(R.string.questionnaire_10_number)
            13 -> resource.getString(R.string.questionnaire_11_number)
            else -> return
        }
        mutableQuestionNumberLabel.postValue(label)
    }

    private fun setQuestionChildLabels(page: Int) {
        val labels: List<String> = when (page) {
            3 -> resource.getStringArray(R.array.questionnaire_sub_first_half_numbers)
            4 -> resource.getStringArray(R.array.questionnaire_sub_latter_half_numbers)
            5 -> resource.getStringArray(R.array.questionnaire_sub_first_half_numbers)
            6 -> resource.getStringArray(R.array.questionnaire_sub_first_half_numbers)
            10 -> resource.getStringArray(R.array.questionnaire_sub_first_half_numbers)
            11 -> resource.getStringArray(R.array.questionnaire_sub_latter_half_numbers)
            13 -> resource.getStringArray(R.array.questionnaire_sub_first_half_numbers)
            else -> return
        }.toList()

        mutableQuestionChildNumberLabels.postValue(labels)
    }

    private fun setQuestionMessage(page: Int) {
        val message: String = when (page) {
            1 -> resource.getString(R.string.questionnaire_1_message)
            2 -> resource.getString(R.string.questionnaire_2_message)
            3 -> resource.getString(R.string.questionnaire_3_message)
            4 -> resource.getString(R.string.questionnaire_3_message)
            5 -> resource.getString(R.string.questionnaire_4_message)
            6 -> resource.getString(R.string.questionnaire_5_message)
            7 -> resource.getString(R.string.questionnaire_6_message)
            8 -> resource.getString(R.string.questionnaire_7_message)
            9 -> resource.getString(R.string.questionnaire_8_message)
            10 -> resource.getString(R.string.questionnaire_9_message)
            11 -> resource.getString(R.string.questionnaire_9_message)
            12 -> resource.getString(R.string.questionnaire_10_message)
            13 -> resource.getString(R.string.questionnaire_11_message)
            else -> return
        }
        mutableQuestionMessage.postValue(message)
    }

    private fun setQuestionChildMessages(page: Int) {
        val messages: List<String> = when (page) {
            3 -> resource.getStringArray(R.array.questionnaire_3_1_sub_messages)
            4 -> resource.getStringArray(R.array.questionnaire_3_2_sub_messages)
            5 -> resource.getStringArray(R.array.questionnaire_4_sub_messages)
            6 -> resource.getStringArray(R.array.questionnaire_5_sub_messages)
            10 -> resource.getStringArray(R.array.questionnaire_9_1_sub_messages)
            11 -> resource.getStringArray(R.array.questionnaire_9_2_sub_messages)
            13 -> resource.getStringArray(R.array.questionnaire_11_sub_messages)
            else -> return
        }.toList()

        mutableQuestionChildMessages.postValue(messages)
    }

    private fun setAnswerChoiceMessages(page: Int) {
        val messages: List<String> = when (page) {
            1 -> resource.getStringArray(R.array.questionnaire_1_answers)
            2 -> resource.getStringArray(R.array.questionnaire_2_answers)
            3 -> resource.getStringArray(R.array.questionnaire_3_answers)
            4 -> resource.getStringArray(R.array.questionnaire_3_answers)
            5 -> resource.getStringArray(R.array.questionnaire_4_answers)
            6 -> resource.getStringArray(R.array.questionnaire_5_answers)
            7 -> resource.getStringArray(R.array.questionnaire_6_answers)
            8 -> resource.getStringArray(R.array.questionnaire_7_answers)
            9 -> resource.getStringArray(R.array.questionnaire_8_answers)
            10 -> resource.getStringArray(R.array.questionnaire_9_answers)
            11 -> resource.getStringArray(R.array.questionnaire_9_answers)
            12 -> resource.getStringArray(R.array.questionnaire_10_answers)
            13 -> resource.getStringArray(R.array.questionnaire_11_answers)
            else -> return
        }.toList()
        mutableAnswerChoiceMessages.postValue(messages)
    }

    private fun setAnswerChoiceMessageVisible(page: Int) {
        val isVisible =
            (page == 5 || page == 6 || page == 8 || page == 10 || page == 11 || page == 13)
        mutableAnswerChoiceMessageVisible.postValue(isVisible)
    }
}
