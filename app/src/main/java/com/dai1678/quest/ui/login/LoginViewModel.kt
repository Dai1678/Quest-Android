package com.dai1678.quest.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dai1678.quest.repository.LoginRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class LoginViewModel : ViewModel() {
    private val parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default
    private val coroutineScope = CoroutineScope(coroutineContext)

    private val loginRepository = LoginRepository.getInstance()

    var username = MutableLiveData<String>()
    var password = MutableLiveData<String>()

    private var _loginStatus = MutableLiveData<Boolean>()
        set(value) {
            _loginStatus.value = false
            field = value
        }
    val loginStatus: LiveData<Boolean> = _loginStatus

    private fun isValid() =
        !username.value.isNullOrBlank() && !password.value.isNullOrBlank()

    val canSubmit = MediatorLiveData<Boolean>().also { result ->
        result.addSource(username) { result.value = isValid() }
        result.addSource(password) { result.value = isValid() }
    }

    // ログインボタンの処理
    fun onClickLogin() {

        coroutineScope.launch {
            val response = loginRepository.login(
                username.value!!, password.value!!
            )
            response?.let {
                // TODO authTokenをprefに保存
                _loginStatus.postValue(it.auth)
            }
        }
    }

    fun cancelRequests() = coroutineContext.cancel()
}
