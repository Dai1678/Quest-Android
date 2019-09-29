package com.dai1678.quest.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dai1678.quest.repository.DoctorRepository
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val repository: DoctorRepository = DoctorRepository.getInstance()

    // ログインボタンの処理
    fun getDoctorList() {
        viewModelScope.launch {
            // TODO 医者ユーザー一覧取得処理
        }
    }
}
