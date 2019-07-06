package com.dai1678.quest.ui.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dai1678.quest.Quest
import com.dai1678.quest.entity.Doctor
import com.dai1678.quest.repository.LoginRepository
import com.dai1678.quest.ui.login.LoginViewModel.AuthenticationState.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class LoginViewModel : ViewModel() {

    enum class AuthenticationState {
        AUTHENTICATED, // Initial state, the user needs to authenticate
        UNAUTHENTICATED, // The user has authenticated successfully
        INVALID_AUTHENTICATION // Authentication failed
    }

    private val parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default
    private val coroutineScope = CoroutineScope(coroutineContext)

    private val loginRepository = LoginRepository.getInstance()
    private val context = Quest.instance

    var username = MutableLiveData<String>()
    var password = MutableLiveData<String>()

    private var _authenticationState = MutableLiveData<AuthenticationState>()
    val authenticationState: LiveData<AuthenticationState> = _authenticationState

    private var _loginUser = MutableLiveData<Doctor>()
    val loginUser: LiveData<Doctor> = _loginUser

    fun refuseAuthentication() {
        _authenticationState.value = UNAUTHENTICATED
    }

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
                if (it.auth) {
                    saveLoginDataToPref(it.token, it.user?.hospitalId)
                    _authenticationState.postValue(AUTHENTICATED)
                } else {
                    _authenticationState.postValue(INVALID_AUTHENTICATION)
                }
            }
        }
    }

    fun cancelRequests() = coroutineContext.cancel()

    private fun saveLoginDataToPref(token: String?, hospitalId: String?) {
        token?.let {
            val preferences = context.getSharedPreferences("DataStore", Context.MODE_PRIVATE)
            preferences.edit().apply {
                putString("hospitalId", hospitalId)
                putString("token", it)
                apply()
            }
        }
    }
}
