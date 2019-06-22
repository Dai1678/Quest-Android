package com.dai1678.quest.ui.login

import android.util.Log
import android.view.View
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dai1678.quest.repository.HospitalRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class LoginViewModel : ViewModel() {
    private val parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default
    private val coroutineScope = CoroutineScope(coroutineContext)

    private val hospitalRepository = HospitalRepository.getInstance()

    var hospitalNumber = MutableLiveData<String>()
    var password = MutableLiveData<String>()

    private fun isValid() =
        !hospitalNumber.value.isNullOrBlank() && !password.value.isNullOrBlank()

    val canSubmit = MediatorLiveData<Boolean>().also { result ->
        result.addSource(hospitalNumber) { result.value = isValid() }
        result.addSource(password) { result.value = isValid() }
    }

    // ログインボタンの処理
    fun onClickLogin(view: View) {
        coroutineScope.launch {
            val response = hospitalRepository.login(
                hospitalNumber.value!!.toLong(), password.value!!
            )
            response?.let {
                startNavigation(it.result, view)
            }
        }
    }

    fun cancelRequests() = coroutineContext.cancel()

    // 画面遷移
    private fun startNavigation(result: Boolean, view: View) {
        if (result) {
            // TODO Navigationで画面遷移
            Log.d("Login", "ログインしました")
        } else {
            // TODO ログイン失敗
            Log.d("Login", "ログインに失敗しました")
        }
    }

    fun onClickTest() {
        Log.d("test", "test")
    }
}
