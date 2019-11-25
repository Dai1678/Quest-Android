package com.dai1678.quest.ui.registerUser

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterUserViewModel : ViewModel() {
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

    // TODO カナ入力制限処理を入れる

    val canSubmit = MediatorLiveData<Boolean>().also { result ->
        result.addSource(firstName) { result.value = isValidInput() }
        result.addSource(lastName) { result.value = isValidInput() }
        result.addSource(firstReadName) { result.value = isValidInput() }
        result.addSource(lastReadName) { result.value = isValidInput() }
        result.addSource(ageRange) { result.value = isValidInput() }
        result.addSource(gender) { result.value = isValidInput() }
    }

    fun submitUserData() {
        // TODO ユーザー作成処理(Repository) を使う
    }
}
