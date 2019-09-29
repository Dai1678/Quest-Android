package com.dai1678.quest.ui.create

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.*
import com.dai1678.quest.entity.BaseResponse
import com.dai1678.quest.entity.Patient
import com.dai1678.quest.entity.Patient2
import com.dai1678.quest.repository.PatientRepository
import com.dai1678.quest.util.PreferenceService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.CoroutineContext

class CreatePatientViewModel : ViewModel() {

    private val patientRepository = PatientRepository.getInstance()

    var firstName = MutableLiveData<String>()
    var lastName = MutableLiveData<String>()

    private var _response = MutableLiveData<BaseResponse>()
    val response: LiveData<BaseResponse> = _response

    private fun isValidDetailInfo() =
        !firstName.value.isNullOrBlank() && !lastName.value.isNullOrBlank()

    val canSubmit = MediatorLiveData<Boolean>().also { result ->
        result.addSource(firstName) { result.value = isValidDetailInfo() }
        result.addSource(lastName) { result.value = isValidDetailInfo() }
    }

    @SuppressLint("SimpleDateFormat")
    fun onClickRegister() {
        viewModelScope.launch {
            val id = UUID.randomUUID().toString()
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

            val patient = Patient2(
                id,
                firstName.value!!,
                lastName.value!!,
                dateFormat.format(Date()),
                emptyList()
            )

            val response = patientRepository.createPatient(patient) ?: return@launch
            _response.postValue(response)
        }
    }
}
