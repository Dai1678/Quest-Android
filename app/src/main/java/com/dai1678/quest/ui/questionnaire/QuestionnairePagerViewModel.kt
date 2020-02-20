package com.dai1678.quest.ui.questionnaire

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dai1678.quest.enums.Question
import com.dai1678.quest.model.PatientDetail
import com.dai1678.quest.model.ScreenLog
import com.google.firebase.database.FirebaseDatabase
import java.util.Date

private const val SCREEN_LOG_CHILD = "screenLog"

/**
 * 回答画面 ViewPager2を管理できるViewModel
 */
class QuestionnairePagerViewModel : ViewModel() {
    var callBack: CallBack? = null

    private val fireBaseDatabase = FirebaseDatabase.getInstance()

    private var scrollCount = 0

    private val mutableCurrentPage = MutableLiveData(0)
    val currentPage: LiveData<Int> = mutableCurrentPage

    private val mutableIsVisibleBackButton = MutableLiveData<Boolean>(true)
    val isVisibleBackButton: LiveData<Boolean> = mutableIsVisibleBackButton

    private val mutableIsVisibleNextButton = MutableLiveData<Boolean>(true)
    val isVisibleNextButton: LiveData<Boolean> = mutableIsVisibleNextButton

    // スクリーンログをFireBase Realtime Databaseに保存
    fun sendScreenLog(page: Int, patientDetail: PatientDetail) {

        scrollCount++

        val screenLog = ScreenLog(
            patientId = patientDetail.id,
            currentPage = page,
            currentTime = Date().time,
            scrollCount = scrollCount,
            gender = patientDetail.gender,
            ageRange = patientDetail.ageRange
        )

        fireBaseDatabase.reference.child(SCREEN_LOG_CHILD).push().setValue(screenLog)
    }

    // 現在のページ番号を保存
    fun setCurrentPage(page: Int) {
        mutableCurrentPage.value = page
    }

    /**
     * ページが移動した際に以下を行う
     *
     * Toolbarのtitleを変更する
     * 遷移ボタンのVisibleを変更する
     */
    fun update() {
        val question = Question.valueOf(currentPage.value ?: 0)
        callBack?.updateToolbarTitle(question)
        when (question) {
            Question.PAGE0 -> {
                mutableIsVisibleBackButton.value = false
                mutableIsVisibleNextButton.value = true
            }
            Question.PAGE14 -> {
                mutableIsVisibleBackButton.value = true
                mutableIsVisibleNextButton.value = false
            }
            else -> {
                mutableIsVisibleBackButton.value = true
                mutableIsVisibleNextButton.value = true
            }
        }
    }

    interface CallBack {
        /**
         * ページ移動時にToolBarのtitleを変更
         */
        fun updateToolbarTitle(question: Question)
    }
}
