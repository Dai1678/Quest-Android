package com.dai1678.quest.ui.questionnaire

import android.util.Log
import android.widget.RadioGroup
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.dai1678.quest.entity.Questionnaire
import com.dai1678.quest.entity.QuestionnaireResult
import com.dai1678.quest.repository.QuestionnaireRepository
import com.dai1678.quest.util.PreferenceService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class QuestionnaireViewModel : ViewModel() {

    private val parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default
    private val coroutineScope = CoroutineScope(coroutineContext)

    private val questionnaireRepository = QuestionnaireRepository.getInstance()

    var selectRadioButtonIds = arrayOfNulls<Int>(36)

    private fun submitQuestionnaire() {
        val token = PreferenceService.getAuthToken()
    }
}
