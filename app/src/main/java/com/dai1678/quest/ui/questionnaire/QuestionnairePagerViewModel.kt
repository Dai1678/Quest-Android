package com.dai1678.quest.ui.questionnaire

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dai1678.quest.enums.Question
import com.dai1678.quest.model.ScreenLog
import com.google.firebase.database.FirebaseDatabase
import java.util.Date

private const val SCREEN_LOG_CHILD = "screenLog"

/**
 * 回答画面 ViewPager2の管理ViewModelオブジェクト
 * @property fireBaseDatabase Firebase Realtime Databaseのインスタンス
 * @property scrollCount ページを切り替えた回数
 * @property currentPage 現在のページ番号
 * @property isVisibleBackButton trueの場合、「前に戻る」ボタンを表示する
 * @property isVisibleNextButton trueの場合、「次へ進む」ボタンを表示する
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

    /**
     * スクリーンログをFireBase Realtime Databaseに保存
     * ページが切り替わるごとに行われる
     * @param page 現在のページ番号
     * @param userId 受検者のid
     * @param userGender 受検者の性別
     * @param userAgeRange 受検者の年齢範囲
     */
    fun sendScreenLog(page: Int, userId: String, userGender: String, userAgeRange: String) {
        scrollCount++

        val screenLog = ScreenLog(
            patientId = userId,
            currentPage = page,
            currentTime = Date().time,
            scrollCount = scrollCount,
            gender = userGender,
            ageRange = userAgeRange
        )

        fireBaseDatabase.reference.child(SCREEN_LOG_CHILD).push().setValue(screenLog)
    }

    /**
     * 現在のページ番号を保存
     * @param page 現在のページ番号
     */
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
         * @param question Question Enum
         */
        fun updateToolbarTitle(question: Question)
    }
}
