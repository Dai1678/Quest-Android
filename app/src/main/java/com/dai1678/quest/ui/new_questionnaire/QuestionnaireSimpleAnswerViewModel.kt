package com.dai1678.quest.ui.new_questionnaire

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.dai1678.quest.App
import com.dai1678.quest.R

class QuestionnaireSimpleAnswerViewModel : ViewModel() {

    private val resource = App.instance.resources

    private val mutableQuestionNumberLabel = MutableLiveData<String>()
    val questionNumberLabel: LiveData<String> = mutableQuestionNumberLabel

    private val mutableQuestionMessage = MutableLiveData<String>()
    val questionMessage: LiveData<String> = mutableQuestionMessage

    // 5択と6択しかない
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
        setQuestionNumberLabel(page)
        setQuestionMessage(page)
        setAnswerChoiceMessages(page)
        setAnswerChoiceMessageVisible(page)
    }

    private fun setQuestionNumberLabel(page: Int) {
        val label: String = when (page) {
            1 -> resource.getString(R.string.questionnaire_1_number)
            2 -> resource.getString(R.string.questionnaire_2_number)
            7 -> resource.getString(R.string.questionnaire_6_number)
            8 -> resource.getString(R.string.questionnaire_7_number)
            9 -> resource.getString(R.string.questionnaire_8_number)
            12 -> resource.getString(R.string.questionnaire_10_number)
            else -> return
        }
        mutableQuestionNumberLabel.postValue(label)
    }

    private fun setQuestionMessage(page: Int) {
        val message: String = when (page) {
            1 -> resource.getString(R.string.questionnaire_1_message)
            2 -> resource.getString(R.string.questionnaire_2_message)
            7 -> resource.getString(R.string.questionnaire_6_message)
            8 -> resource.getString(R.string.questionnaire_7_message)
            9 -> resource.getString(R.string.questionnaire_8_message)
            12 -> resource.getString(R.string.questionnaire_10_message)
            else -> return
        }
        mutableQuestionMessage.postValue(message)
    }

    private fun setAnswerChoiceMessages(page: Int) {
        val messages: List<String> = when (page) {
            1 -> resource.getStringArray(R.array.questionnaire_1_answers)
            2 -> resource.getStringArray(R.array.questionnaire_2_answers)
            7 -> resource.getStringArray(R.array.questionnaire_6_answers)
            8 -> resource.getStringArray(R.array.questionnaire_7_answers)
            9 -> resource.getStringArray(R.array.questionnaire_8_answers)
            12 -> resource.getStringArray(R.array.questionnaire_10_answers)
            else -> return
        }.toList()
        mutableAnswerChoiceMessages.postValue(messages)
    }

    private fun setAnswerChoiceMessageVisible(page: Int) {
        val isVisible = page == 8
        mutableAnswerChoiceMessageVisible.postValue(isVisible)
    }
}
