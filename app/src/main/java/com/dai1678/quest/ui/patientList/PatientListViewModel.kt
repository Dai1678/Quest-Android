package com.dai1678.quest.ui.patientList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dai1678.quest.R
import com.dai1678.quest.entity.Patient
import com.dai1678.quest.net.NetworkResult
import com.dai1678.quest.repository.PatientRepository
import com.dai1678.quest.util.Event
import kotlinx.coroutines.launch

class PatientListViewModel : ViewModel() {

    private val repository = PatientRepository.getInstance()

    private val mutableIsLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = mutableIsLoading

    private val mutableUsers = MutableLiveData<List<Patient>>()
    val users: LiveData<List<Patient>> = mutableUsers

    private val mutableSnackBarText = MutableLiveData<Event<Int>>()
    val snackBarText: LiveData<Event<Int>> = mutableSnackBarText

    init {
        getUsers()
    }

    fun getUsers() {
        mutableIsLoading.postValue(true)
        viewModelScope.launch {
            when (val result = repository.getUsers()) {
                is NetworkResult.Success -> {
                    mutableUsers.postValue(result.value.list)
                }
                is NetworkResult.Error -> {
                    Log.e("PatientListViewModel", result.exception.toString())
                    showLoadingFailureMessage()
                }
            }
        }
        mutableIsLoading.postValue(false)
    }

    private fun showLoadingFailureMessage() {
        val snackBarTextId = R.string.patient_list_error_loading_message
        mutableSnackBarText.postValue(Event((snackBarTextId)))
    }
}
