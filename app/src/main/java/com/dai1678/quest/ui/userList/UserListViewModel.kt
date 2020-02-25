package com.dai1678.quest.ui.userList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dai1678.quest.R
import com.dai1678.quest.model.User
import com.dai1678.quest.model.PatientListResponse
import com.dai1678.quest.net.NetworkResult
import com.dai1678.quest.repository.UserRepository
import com.dai1678.quest.util.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * 受検者リスト画面 ViewModelオブジェクト
 *
 * @property isLoading SwipeRefreshLayoutのローディング管理 trueの場合アニメーションさせる
 * @property users 受検者リストデータ
 * @property snackBarText SnackBarに表示する文言の文字列リソース
 */
class UserListViewModel : ViewModel() {
    private val repository = UserRepository.getInstance()

    private val mutableIsLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = mutableIsLoading

    private val mutableUsers = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = mutableUsers

    private val mutableSnackBarText = MutableLiveData<Event<Int>>()
    val snackBarText: LiveData<Event<Int>> = mutableSnackBarText

    init {
        getUsers()
    }

    /**
     * 受検者データの取得
     */
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

    /**
     * 受検者データを返す
     * @return NetworkResult<PatientListResponse>
     */
    private suspend fun fetchUsers(): NetworkResult<PatientListResponse> =
        withContext(Dispatchers.IO) {
            repository.getUsers()
        }

    /**
     * 受検者データ失敗時のメッセージ設定
     */
    private fun showLoadingFailureMessage() {
        val snackBarTextId = R.string.user_list_error_loading_message
        mutableSnackBarText.value = Event((snackBarTextId))
    }
}
