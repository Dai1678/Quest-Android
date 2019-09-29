package com.dai1678.quest.ui.patientList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dai1678.quest.entity.Patient
import com.dai1678.quest.repository.PatientRepository
import kotlinx.coroutines.launch
import kotlin.math.ceil

class PatientListViewModel : ViewModel() {

    private val patientRepository = PatientRepository.getInstance()

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _patientsList = MutableLiveData<List<Patient?>>()
    val patientList: LiveData<List<Patient?>> = _patientsList

    private fun getPatientsList() {
        viewModelScope.launch {
            var page = 1
            val limit = 100

            var result = patientRepository.getPatientList(limit, page)
            result?.let { resultFirst ->
                val allPatientsList = resultFirst.list
                val totalPatientsNumber = resultFirst.total

                val totalPage = ceil((totalPatientsNumber / limit).toDouble()).toInt()

                while (totalPage > 1 && totalPage >= page) {
                    page++
                    result = patientRepository.getPatientList(limit, page)
                    result?.let { resultLater ->
                        allPatientsList.plus(resultLater.list)
                    }
                }

                _patientsList.postValue(allPatientsList)
            }

            _isLoading.postValue(false)
        }
    }

    fun onRefresh() {
        _isLoading.value = true
        getPatientsList()
    }
}
