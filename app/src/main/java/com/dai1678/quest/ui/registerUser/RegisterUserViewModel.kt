package com.dai1678.quest.ui.registerUser

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dai1678.quest.R
import com.dai1678.quest.model.DefaultResponse
import com.dai1678.quest.model.User
import com.dai1678.quest.net.NetworkResult
import com.dai1678.quest.repository.UserRepository
import com.dai1678.quest.util.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * 受検者登録画面 ViewModelオブジェクト
 *
 * @property snackBarText SnackBarに表示する文言の文字列リソース
 * @property firstName 入力された受検者の名
 * @property lastName 入力された受検者の姓
 * @property firstReadName 入力された受検者の名(読み仮名)
 * @property lastReadName 入力された受検者の姓(読み仮名)
 * @property ageRange 入力された受検者の年齢範囲
 * @property gender 入力された受検者の性別
 * @property canSubmit 入力フォームが全て埋まっていれば、trueを返す
 */
class RegisterUserViewModel : ViewModel() {
    private val repository = UserRepository.getInstance()
    var callback: Callback? = null

    private val mutableSnackBarText = MutableLiveData<Event<Int>>()
    val snackBarText: LiveData<Event<Int>> = mutableSnackBarText

    var firstName = MutableLiveData<String>()
    var lastName = MutableLiveData<String>()
    var firstReadName = MutableLiveData<String>()
    var lastReadName = MutableLiveData<String>()

    var ageRange = MutableLiveData<String>()
    var gender = MutableLiveData<String>()

    val canSubmit = MediatorLiveData<Boolean>().also { result ->
        result.addSource(firstName) { result.value = isValidInput() }
        result.addSource(lastName) { result.value = isValidInput() }
        result.addSource(firstReadName) { result.value = isValidInput() }
        result.addSource(lastReadName) { result.value = isValidInput() }
        result.addSource(ageRange) { result.value = isValidInput() }
        result.addSource(gender) { result.value = isValidInput() }
    }

    /**
     * 入力フォームの確認
     */
    private fun isValidInput() =
        firstName.value.isNullOrBlank().not() &&
                lastName.value.isNullOrBlank().not() &&
                firstReadName.value.isNullOrBlank().not() &&
                lastReadName.value.isNullOrBlank().not() &&
                ageRange.value.isNullOrBlank().not() &&
                gender.value.isNullOrBlank().not()

    // TODO ひらがな入力制限処理を入れる

    /**
     * 登録ボタンを押したときの処理
     */
    fun registerUserData() {
        viewModelScope.launch {
            when (val result = postUserData()) {
                is NetworkResult.Success -> {
                    showRegisteredMessage(R.string.create_user_success_label)
                    callback?.finishQuestionnaire()
                }
                is NetworkResult.Error -> {
                    Log.e("RegisterUserViewModel", result.exception.toString())
                    showRegisteredMessage(R.string.create_user_failure_label)
                }
            }
        }
    }

    /**
     * 受検者登録処理
     * @return NetworkResult<DefaultResponse>
     */
    private suspend fun postUserData(): NetworkResult<DefaultResponse> =
        withContext(Dispatchers.IO) {
            val newPatient = User(
                firstName = firstName.value.orEmpty(),
                lastName = lastName.value.orEmpty(),
                firstNameReading = firstReadName.value.orEmpty(),
                lastNameReading = lastReadName.value.orEmpty(),
                gender = gender.value.orEmpty(),
                ageRange = ageRange.value.orEmpty()
            )
            repository.createUser(newPatient)
        }

    /**
     * 受検者登録処理後に表示するメッセージ設定
     * @param messageResId 表示する文言の文字列リソース
     */
    private fun showRegisteredMessage(messageResId: Int) {
        mutableSnackBarText.postValue(Event((messageResId)))
    }

    interface Callback {
        /**
         * 送信完了時の処理
         */
        fun finishQuestionnaire()
    }
}
