package com.dai1678.quest.ui.questionnaire

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dai1678.quest.R
import com.dai1678.quest.enums.Question
import com.dai1678.quest.model.Questionnaire
import com.dai1678.quest.model.QuestionnaireResult
import com.dai1678.quest.net.NetworkResult
import com.dai1678.quest.repository.QuestionnaireRepository
import com.dai1678.quest.util.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * 回答画面のViewModelオブジェクト
 *
 * @property questionMessage 設問文章
 * @property answerChoiceMessages 回答項目文言
 * @property questionnaireResult 回答結果
 */
class QuestionnaireAnswerViewModel : ViewModel() {
    private val questionnaireRepository = QuestionnaireRepository.getInstance()
    var callback: Callback? = null

    private val mutableSnackBarText = MutableLiveData<Event<Int>>()
    val snackBarText: LiveData<Event<Int>> = mutableSnackBarText

    private val mutableQuestionMessage = MutableLiveData<String>()
    val questionMessage: LiveData<String> = mutableQuestionMessage

    private val mutableAnswerChoiceMessages = MutableLiveData<Array<String>>()
    val answerChoiceMessages: LiveData<Array<String>> = mutableAnswerChoiceMessages

    private var questionnaireResult = QuestionnaireResult()

    /**
     * 回答結果の一時保存
     * @param page 現在表示している回答画面のページ番号
     * @param answer 選択した回答項目の番号
     * @param index 結果保存の配列のindex (小問の回答の場合のみ使用)
     */
    fun setQuestionnaireResult(page: Int, answer: Int, index: Int = 0) {
        when (Question.valueOf(page)) {
            Question.PAGE1 -> questionnaireResult.page1Answer = answer
            Question.PAGE2 -> questionnaireResult.page2Answer = answer
            Question.PAGE3 -> questionnaireResult.page3Answer[index] = answer
            Question.PAGE4 -> questionnaireResult.page4Answer[index] = answer
            Question.PAGE5 -> questionnaireResult.page5Answer[index] = answer
            Question.PAGE6 -> questionnaireResult.page6Answer[index] = answer
            Question.PAGE7 -> questionnaireResult.page7Answer = answer
            Question.PAGE8 -> questionnaireResult.page8Answer = answer
            Question.PAGE9 -> questionnaireResult.page9Answer = answer
            Question.PAGE10 -> questionnaireResult.page10Answer[index] = answer
            Question.PAGE11 -> questionnaireResult.page11Answer[index] = answer
            Question.PAGE12 -> questionnaireResult.page12Answer = answer
            Question.PAGE13 -> questionnaireResult.page13Answer[index] = answer
            else -> return
        }
    }

    /**
     * 設問文章と回答項目文言を更新
     * @param message 更新後の設問文章
     * @param answers 更新後の回答項目文言
     */
    fun update(message: String, answers: Array<String>) {
        mutableQuestionMessage.value = message
        mutableAnswerChoiceMessages.value = answers
    }

    /**
     * 結果送信ボタンを押したときの処理
     * @param userId ユーザーのid
     */
    fun sendQuestionnaireResult(userId: String) {
        viewModelScope.launch {
            when (val result = postResult(userId)) {
                is NetworkResult.Success -> {
                    showPostedResultMessage(R.string.questionnaire_post_success_text)
                    callback?.finishQuestionnaire()
                }
                is NetworkResult.Error -> {
                    Log.e("QuestionnaireAnswerVM", result.exception.toString())
                    showPostedResultMessage(R.string.questionnaire_post_failure_text)
                }
            }
        }
    }

    /**
     * 回答結果送信処理
     * @param userId ユーザーのid
     * @return NetworkResult<Questionnaire>
     */
    private suspend fun postResult(userId: String): NetworkResult<Questionnaire> =
        withContext(Dispatchers.IO) {
            val questionnaire = Questionnaire(
                result = questionnaireResult,
                patientId = userId
            )
            questionnaireRepository.createResult(questionnaire)
        }

    /**
     * 結果送信処理後のメッセージ設定
     * @param messageResId Toastに表示する文言の文字列リソース
     */
    private fun showPostedResultMessage(messageResId: Int) {
        mutableSnackBarText.postValue(Event((messageResId)))
    }

    interface Callback {
        /**
         * 結果送信完了後の処理
         */
        fun finishQuestionnaire()
    }
}
