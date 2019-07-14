package com.dai1678.quest.ui.create

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dai1678.quest.entity.BaseResponse
import com.dai1678.quest.entity.Patient
import com.dai1678.quest.repository.PatientRepository
import com.dai1678.quest.util.PreferenceService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*
import kotlin.coroutines.CoroutineContext

class CreatePatientViewModel : ViewModel() {

    private val parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default
    private val coroutineScope = CoroutineScope(coroutineContext)

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

    fun onClickRegister() {
        val token = PreferenceService.getAuthToken()
        Log.d("token", token)

        token?.let {
            coroutineScope.launch {
                val userName = UUID.randomUUID().toString()
                val hospitalId = PreferenceService.getLoggedInHospitalId() ?: return@launch

                val patient = Patient(
                    userName,
                    firstName.value!!,
                    lastName.value!!,
                    null,
                    Date().toString(),
                    hospitalId
                )

                val response = patientRepository.register(it, patient) ?: return@launch
                _response.postValue(response)
            }
        }
    }
}
