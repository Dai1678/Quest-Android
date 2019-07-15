package com.dai1678.quest.ui.patientList

import androidx.lifecycle.*
import com.dai1678.quest.entity.Patient
import com.dai1678.quest.repository.PatientRepository
import com.dai1678.quest.util.PreferenceService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.math.ceil

class PatientListViewModel : ViewModel() {

    private val parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default
    private val coroutineScope = CoroutineScope(coroutineContext)

    private val patientRepository = PatientRepository.getInstance()

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _patientsList = MutableLiveData<List<Patient>?>()
    val patientList: LiveData<List<Patient>?> = _patientsList

    init {
        getPatientsList()
    }

    private fun getPatientsList() {
        val token = PreferenceService.getAuthToken()
        val hospitalId = PreferenceService.getLoggedInHospitalId()

        if (token != null && hospitalId != null) {
            coroutineScope.launch {
                var page = 1
                val limit = 100

                var result = patientRepository.getPatientList(token, hospitalId, page, limit)
                result?.let { resultFirst ->
                    val allPatientsList = resultFirst.list
                    val totalPatientsNumber = resultFirst.total

                    val totalPage = ceil((totalPatientsNumber / limit).toDouble()).toInt()

                    while (totalPage > 1 && totalPage >= page) {
                        page++
                        result = patientRepository.getPatientList(token, hospitalId, page, limit)
                        result?.let { resultLater ->
                            allPatientsList.plus(resultLater.list)
                        }
                    }

                    _patientsList.postValue(allPatientsList)
                }

                _isLoading.postValue(false)
            }
        }
    }

    fun onRefresh() {
        _isLoading.value = true
        getPatientsList()
    }
}
