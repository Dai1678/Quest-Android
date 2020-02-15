package com.dai1678.quest.ui.userList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dai1678.quest.R
import com.dai1678.quest.entity.Patient
import com.dai1678.quest.entity.PatientListResponse
import com.dai1678.quest.net.NetworkResult
import com.dai1678.quest.repository.UserRepository
import com.dai1678.quest.util.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * 受検者リスト画面 ViewModel層
 */
class UserListViewModel : ViewModel() {

    private val repository = UserRepository.getInstance()

    // SwipeRefreshLayoutのローディング管理
    private val mutableIsLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = mutableIsLoading

    // 受検者リストデータ
    private val mutableUsers = MutableLiveData<List<Patient>>()
    val users: LiveData<List<Patient>> = mutableUsers

    private val mutableSnackBarText = MutableLiveData<Event<Int>>()
    val snackBarText: LiveData<Event<Int>> = mutableSnackBarText

    init {
        getUsers()
    }

    // 受検者データの取得
    fun getUsers() {
        mutableIsLoading.value = true
        viewModelScope.launch {
            when (val result = fetchUsers()) {
                is NetworkResult.Success -> {
                    mutableUsers.value = result.value.list
                }
                is NetworkResult.Error -> {
                    Log.e("PatientListViewModel", result.exception.toString())
                    showLoadingFailureMessage()
                }
            }
            mutableIsLoading.value = false
        }
    }

    private suspend fun fetchUsers(): NetworkResult<PatientListResponse> =
        withContext(Dispatchers.IO) {
            repository.getUsers()
        }

    // 受検者データ失敗時のメッセージ設定
    private fun showLoadingFailureMessage() {
        val snackBarTextId = R.string.user_list_error_loading_message
        mutableSnackBarText.value = Event((snackBarTextId))
    }
}
