package com.dai1678.quest.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.dai1678.quest.entity.ErrorResponse
import com.dai1678.quest.entity.SnackBarMessage
import com.dai1678.quest.repository.DoctorRepository
import com.dai1678.quest.util.ActionLiveData
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers

class LoginViewModel : ViewModel() {

    private val repository: DoctorRepository = DoctorRepository.getInstance()

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

    fun getDoctorList() = liveData(Dispatchers.IO) {
        try {
            val response = repository.getDoctorList()
            if (response.isSuccessful) {
                emit(response.body())
            } else {
                response.errorBody()?.let { errorBody ->
                    val moshi = Moshi.Builder().build()
                    val error =
                        moshi.adapter(ErrorResponse::class.java).fromJson(errorBody.string())
                    Log.e("LoginViewModel", error.toString())
                    emit(null)
                    error?.let {
                        snackBarAction.sendActionBackGround(SnackBarMessage(it.message))
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("LoginViewModel", e.toString())
            snackBarAction.sendActionBackGround(SnackBarMessage("データの取得に失敗しました"))
        } finally {
            isLoadingLiveData.postValue(false)
        }
    }

    fun reloadDoctorList() {
        isLoadingLiveData.value = true
    }
}
