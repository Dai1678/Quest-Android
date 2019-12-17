package com.dai1678.quest.ui.questionnaire

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dai1678.quest.App
import com.dai1678.quest.R
import com.dai1678.quest.entity.Questionnaire
import com.dai1678.quest.entity.QuestionnaireResult
import com.dai1678.quest.entity.ScreenLog
import com.dai1678.quest.repository.QuestionnaireRepository
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.UUID

class QuestionnaireAnswerViewModel : ViewModel() {
    private val fireBaseDatabase = FirebaseDatabase.getInstance()
    private val questionnaireRepository = QuestionnaireRepository.getInstance()
    private val resource = App.instance.resources
    var callback: Callback? = null

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

    @SuppressLint("SimpleDateFormat")
    fun sendQuestionnaireResult(patientId: String) {
        Log.d("answer", questionnaireResult.toString())

        viewModelScope.launch(Dispatchers.Main) {
            val id = UUID.randomUUID().toString()
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            runCatching {
                withContext(Dispatchers.IO) {
                    questionnaireRepository.createResult(
                        Questionnaire(
                            id = id,
                            result = questionnaireResult,
                            createdAt = dateFormat.format(Date()),
                            updatedAt = dateFormat.format(Date()),
                            patientId = patientId
                        )
                    )
                }
            }.onSuccess {
                if (it.isSuccessful) {
                    callback?.finishQuestionnaire()
                } else {
                    callback?.showErrorSnackBar(it.message())
                }
            }.onFailure {
                val errorThrowsMessage = "データの送信に失敗しました"
                Log.e("sendQuestionnaireResult", it.message ?: errorThrowsMessage)
                callback?.showErrorSnackBar(errorThrowsMessage)
            }
        }
    }

    fun sendScreenLog() {
        val screenLog = ScreenLog(
            startTime = 0L,
            endTime = 0L,
            gender = "",
            ageRange = ""
        )

        fireBaseDatabase.reference.child(SCREEN_LOG_CHILD).push().setValue(screenLog)
    }

    interface Callback {
        /**
         * 送信完了
         */
        fun finishQuestionnaire()

        /**
         * スナックバー表示
         */
        fun showErrorSnackBar(message: String)
    }

    companion object {
        private const val SCREEN_LOG_CHILD = "screenLog"
    }
}
