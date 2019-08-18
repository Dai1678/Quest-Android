package com.dai1678.quest.ui.questionnaire

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dai1678.quest.entity.BaseResponse
import com.dai1678.quest.entity.Questionnaire
import com.dai1678.quest.entity.QuestionnaireResult
import com.dai1678.quest.repository.QuestionnaireRepository
import com.dai1678.quest.util.PreferenceService
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class QuestionnaireViewModel : ViewModel() {

    private val questionnaireRepository = QuestionnaireRepository.getInstance()

    private var _response = MutableLiveData<BaseResponse>()
    val response: LiveData<BaseResponse> = _response

    var patientId = MutableLiveData<String>()

    var selectRadioButtonIds = arrayOfNulls<Int>(36)
    var selectRadioButtonTexts = arrayOfNulls<String>(36)

    @SuppressLint("SimpleDateFormat")
    fun submitQuestionnaire(result: QuestionnaireResult, patientId: String) {
        val token = PreferenceService.getAuthToken()

        token?.let {
            viewModelScope.launch {
                val id = UUID.randomUUID().toString()
                val doctorId = PreferenceService.getLoggedInDoctorId()
                val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

                val response = questionnaireRepository.createResult(
                    token,
                    Questionnaire(
                        id = id,
                        result = result,
                        responsibleDoctorId = doctorId!!,
                        createdAt = dateFormat.format(Date()),
                        updatedAt = dateFormat.format(Date()),
                        patientId = patientId
                    )
                )

                _response.postValue(response)
            }
        }
    }
}
