package com.dai1678.quest.ui.questionnaire

import android.annotation.SuppressLint
import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dai1678.quest.R
import com.dai1678.quest.entity.ErrorResponse
import com.dai1678.quest.entity.Question1
import com.dai1678.quest.entity.Question10
import com.dai1678.quest.entity.Question11
import com.dai1678.quest.entity.Question2
import com.dai1678.quest.entity.Question3
import com.dai1678.quest.entity.Question4
import com.dai1678.quest.entity.Question5
import com.dai1678.quest.entity.Question6
import com.dai1678.quest.entity.Question7
import com.dai1678.quest.entity.Question8
import com.dai1678.quest.entity.Question9
import com.dai1678.quest.entity.QuestionSize
import com.dai1678.quest.entity.Questionnaire
import com.dai1678.quest.entity.QuestionnaireResult
import com.dai1678.quest.entity.SnackBarMessage
import com.dai1678.quest.repository.QuestionnaireRepository
import com.dai1678.quest.util.ActionLiveData
import com.squareup.moshi.Moshi
import java.text.SimpleDateFormat
import java.util.Date
import java.util.UUID
import kotlinx.coroutines.launch

class QuestionnaireViewModel : ViewModel() {

    private val questionnaireRepository = QuestionnaireRepository.getInstance()

    private val snackBarAction = ActionLiveData<SnackBarMessage>()

    fun getSnackBarAction() = snackBarAction

    private val response = MutableLiveData<Questionnaire>()
    fun getResponse(): LiveData<Questionnaire> = response

    private var page = MutableLiveData<Int>()
    fun getPage(): LiveData<Int> = page

    private var patientId: String = ""
    fun getPatientId(): String = patientId
    fun setPatientId(id: String) {
        patientId = id
    }

    var selectRadioButtonPositions = arrayOfNulls<Int>(36)
    var selectRadioButtonTexts = arrayOfNulls<String>(36)

    init {
        page.value = 0
    }

    @SuppressLint("SimpleDateFormat")
    fun submitQuestionnaire(result: QuestionnaireResult) {
        viewModelScope.launch {
            try {
                val id = UUID.randomUUID().toString()
                val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

                val res = questionnaireRepository.createResult(
                    Questionnaire(
                        id = id,
                        result = result,
                        responsibleDoctorId = "empty",
                        createdAt = dateFormat.format(Date()),
                        updatedAt = dateFormat.format(Date()),
                        patientId = patientId
                    )
                )

                if (res.isSuccessful && res.body() != null) {
                    response.postValue(res.body()!!)
                } else {
                    res.errorBody()?.let { errorBody ->
                        val moshi = Moshi.Builder().build()
                        val error =
                            moshi.adapter(ErrorResponse::class.java).fromJson(errorBody.string())
                        Log.e("QuestionnaireEnd", error.toString())
                        error?.let {
                            snackBarAction.sendActionBackGround(SnackBarMessage(it.message))
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("QuestionnaireViewModel", e.toString())
                snackBarAction.sendActionBackGround(SnackBarMessage("データの送信に失敗しました"))
            }
        }
    }

    @StringRes
    fun getQuestionNumberMessageResId(): Int? {
        return when (getPage().value) {
            1 -> R.string.questionnaire_1_number
            2 -> R.string.questionnaire_2_number
            3, 4 -> R.string.questionnaire_3_number
            5 -> R.string.questionnaire_4_number
            6 -> R.string.questionnaire_5_number
            7 -> R.string.questionnaire_6_number
            8 -> R.string.questionnaire_7_number
            9 -> R.string.questionnaire_8_number
            10, 11 -> R.string.questionnaire_9_number
            12 -> R.string.questionnaire_10_number
            13 -> R.string.questionnaire_11_number
            else -> null
        }
    }

    fun getQuestionMainMessageResId(): Int? {
        return when (getPage().value) {
            1 -> Question1.MAIN.resId
            2 -> Question2.MAIN.resId
            3, 4 -> Question3.MAIN.resId
            5 -> Question4.MAIN.resId
            6 -> Question5.MAIN.resId
            7 -> Question6.MAIN.resId
            8 -> Question7.MAIN.resId
            9 -> Question8.MAIN.resId
            10, 11 -> Question9.MAIN.resId
            12 -> Question10.MAIN.resId
            13 -> Question11.MAIN.resId
            else -> null
        }
    }

    fun getQuestionSubMessageResId(): Int? {
        return when (getPage().value) {
            3 -> Question3.SUB1.resId
            4 -> Question3.SUB2.resId
            5 -> Question4.SUB.resId
            6 -> Question5.SUB.resId
            10 -> Question9.SUB1.resId
            11 -> Question9.SUB2.resId
            13 -> Question11.SUB.resId
            else -> null
        }
    }

    fun getQuestionAnswerResId(): Int? {
        return when (getPage().value) {
            1 -> Question1.ANSWERS.resId
            2 -> Question2.ANSWERS.resId
            3, 4 -> Question3.ANSWERS.resId
            5 -> Question4.ANSWERS.resId
            6 -> Question5.ANSWERS.resId
            7 -> Question6.ANSWERS.resId
            8 -> Question7.ANSWERS.resId
            9 -> Question8.ANSWERS.resId
            10, 11 -> Question9.ANSWERS.resId
            12 -> Question10.ANSWERS.resId
            13 -> Question11.ANSWERS.resId
            else -> null
        }
    }

    fun nextPage() {
        page.value = page.value!! + 1
    }

    fun backPage() {
        page.value = page.value!! - 1
    }

    fun getQuestionSizeUntilCurrentPage(currentPage: Int): Int {
        var untilSize = 0
        for (i in 0 until currentPage) {
            untilSize += QuestionSize.values()[i].size
        }
        Log.d("untilSize", untilSize.toString())
        return untilSize
    }
}
