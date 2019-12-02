package com.dai1678.quest.ui.questionnaire

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dai1678.quest.entity.ErrorResponse
import com.dai1678.quest.entity.Questionnaire
import com.dai1678.quest.entity.QuestionnaireResult
import com.dai1678.quest.entity.SnackBarMessage
import com.dai1678.quest.repository.QuestionnaireRepository
import com.dai1678.quest.util.ActionLiveData
import com.squareup.moshi.Moshi
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.UUID

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
}
