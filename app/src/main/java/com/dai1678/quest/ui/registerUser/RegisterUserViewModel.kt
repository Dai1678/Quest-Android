package com.dai1678.quest.ui.registerUser

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dai1678.quest.entity.Patient
import com.dai1678.quest.repository.PatientRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterUserViewModel : ViewModel() {
    private val patientRepository = PatientRepository.getInstance()
    var callback: Callback? = null

    var firstName = MutableLiveData<String>()
    var lastName = MutableLiveData<String>()
    var firstReadName = MutableLiveData<String>()
    var lastReadName = MutableLiveData<String>()

    var ageRange = MutableLiveData<String>()
    var gender = MutableLiveData<String>()

    private fun isValidInput() =
        firstName.value.isNullOrBlank().not() &&
                lastName.value.isNullOrBlank().not() &&
                firstReadName.value.isNullOrBlank().not() &&
                lastReadName.value.isNullOrBlank().not() &&
                ageRange.value.isNullOrBlank().not() &&
                gender.value.isNullOrBlank().not()

    // TODO ひらがな入力制限処理を入れる

    val canSubmit = MediatorLiveData<Boolean>().also { result ->
        result.addSource(firstName) { result.value = isValidInput() }
        result.addSource(lastName) { result.value = isValidInput() }
        result.addSource(firstReadName) { result.value = isValidInput() }
        result.addSource(lastReadName) { result.value = isValidInput() }
        result.addSource(ageRange) { result.value = isValidInput() }
        result.addSource(gender) { result.value = isValidInput() }
    }

    fun registerUserData() {
        viewModelScope.launch(Dispatchers.Main) {
            runCatching {
                withContext(Dispatchers.IO) {
                    patientRepository.createPatient(
                        Patient(
                            firstName = firstName.value.orEmpty(),
                            lastName = lastName.value.orEmpty(),
                            firstNameReading = firstReadName.value.orEmpty(),
                            lastNameReading = lastReadName.value.orEmpty(),
                            gender = gender.value.orEmpty(),
                            ageRange = ageRange.value.orEmpty()
                        )
                    )
                }
            }.onSuccess {
                if (it.isSuccessful) {
                    callback?.finishQuestionnaire()
                } else {
                    callback?.showErrorSnackBar(it.message())
                }
            }.onFailure {
                val errorThrowsMessage = "データの送信に失敗しました"
                Log.e("registerUserData", it.message ?: errorThrowsMessage)
                callback?.showErrorSnackBar(errorThrowsMessage)
            }
        }
    }

    interface Callback {
        /**
         * 送信完了
         */
        fun finishQuestionnaire()

        /**
         * スナックバー表示
         */
        fun showErrorSnackBar(message: String)
    }
}
