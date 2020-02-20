package com.dai1678.quest.ui.questionnaire

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dai1678.quest.R
import com.dai1678.quest.model.Questionnaire
import com.dai1678.quest.model.QuestionnaireResult
import com.dai1678.quest.net.NetworkResult
import com.dai1678.quest.repository.QuestionnaireRepository
import com.dai1678.quest.util.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QuestionnaireAnswerViewModel(application: Application) : AndroidViewModel(application) {
    private val questionnaireRepository = QuestionnaireRepository.getInstance()
    private val resource = application.resources
    var callback: Callback? = null

    private val mutableSnackBarText = MutableLiveData<Event<Int>>()
    val snackBarText: LiveData<Event<Int>> = mutableSnackBarText

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

    @SuppressLint("SimpleDateFormat")
    fun sendQuestionnaireResult(patientId: String) {
        viewModelScope.launch {
            when (val result = postResult(patientId)) {
                is NetworkResult.Success -> {
                    showPostedResultMessage(R.string.questionnaire_post_success_text)
                    callback?.finishQuestionnaire()
                }
                is NetworkResult.Error -> {
                    Log.e("QuestionnaireAnswerVM", result.exception.toString())
                    showPostedResultMessage(R.string.questionnaire_post_failure_text)
                }
            }
        }
    }

    private suspend fun postResult(patientId: String): NetworkResult<Questionnaire> =
        withContext(Dispatchers.IO) {
            val questionnaire = Questionnaire(
                result = questionnaireResult,
                patientId = patientId
            )
            questionnaireRepository.createResult(questionnaire)
        }

    // 結果送信処理後のメッセージ設定
    private fun showPostedResultMessage(messageResId: Int) {
        mutableSnackBarText.postValue(Event((messageResId)))
    }

    interface Callback {
        // 結果送信完了後の処理
        fun finishQuestionnaire()
    }
}
