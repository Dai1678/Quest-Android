package com.dai1678.quest.ui.patientList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.dai1678.quest.entity.ErrorResponse
import com.dai1678.quest.entity.SnackBarMessage
import com.dai1678.quest.repository.PatientRepository
import com.dai1678.quest.util.ActionLiveData
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers

class PatientListViewModel : ViewModel() {

    private val repository = PatientRepository.getInstance()

    private val snackBarAction = ActionLiveData<SnackBarMessage>()

    fun getSnackBarAction() = snackBarAction

    private lateinit var isLoadingLiveData: MutableLiveData<Boolean>

    fun isLoading(): LiveData<Boolean> {
        if (!::isLoadingLiveData.isInitialized) {
            isLoadingLiveData = MutableLiveData()
            isLoadingLiveData.value = true
        }
        return isLoadingLiveData
    }

    fun getPatientList() = liveData(Dispatchers.IO) {
        try {
            val response = repository.getPatientList()
            if (response.isSuccessful && response.body() != null) {
                emit(response.body())
                if (response.body()!!.list.isEmpty()) {
                    snackBarAction.sendActionBackGround(SnackBarMessage("受検可能な患者はいません"))
                }
            } else {
                response.errorBody()?.let { errorBody ->
                    val moshi = Moshi.Builder().build()
                    val error =
                        moshi.adapter(ErrorResponse::class.java).fromJson(errorBody.string())
                    Log.e("PatientListViewModel", error.toString())
                    emit(null)
                    error?.let {
                        snackBarAction.sendActionBackGround(SnackBarMessage(it.message))
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("PatientListViewModel", e.toString())
            snackBarAction.sendActionBackGround(SnackBarMessage("データの取得に失敗しました"))
        } finally {
            isLoadingLiveData.postValue(false)
        }
    }

    fun reloadPatientList() {
        isLoadingLiveData.value = true
    }
}
