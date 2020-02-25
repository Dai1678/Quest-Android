package com.dai1678.quest.ui.questionnaire

import android.content.Context
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import com.dai1678.quest.R
import com.dai1678.quest.databinding.ListItemQuestionnaireChildChoiceBinding
import com.dai1678.quest.enums.Answer
import com.dai1678.quest.enums.Question
import com.dai1678.quest.listener.QuestionnaireAnswerFragmentListener
import com.xwray.groupie.databinding.BindableItem

/**
 * 小問のレイアウト生成用 groupie Item
 * @param context Context
 * @param question Question Enum
 * @param answer Answer Enum
 * @param cacheAnswerArray タップされたRadioButtonのidのキャッシュ用配列
 * @param checkCallbackListener ラジオボタンをタップしたときのコールバック
 */
class QuestionnaireChildItem(
    private val context: Context,
    private val question: Question,
    private val answer: Answer,
    private val cacheAnswerArray: IntArray,
    private val checkCallbackListener: (Int, Int, Int) -> Unit
) : BindableItem<ListItemQuestionnaireChildChoiceBinding>() {
    override fun getLayout(): Int = R.layout.list_item_questionnaire_child_choice

    override fun bind(viewBinding: ListItemQuestionnaireChildChoiceBinding, position: Int) {
        viewBinding.questionnaireSubNumberText.text =
            question.getSubTitleNumbers(context)[position]
        viewBinding.questionnaireSubMessageText.text = question.getSubMessages(context)[position]

        val answerMessages = answer.getAnswers(context)
        viewBinding.answerChildChoice1.text = answerMessages[0]
        viewBinding.answerChildChoice2.text = answerMessages[1]
        viewBinding.answerChildChoice3.text = answerMessages[2]
        viewBinding.answerChildChoice4.apply {
            visibility = if (answerMessages[3].isNotEmpty()) View.VISIBLE else View.GONE
            text = answerMessages[3]
        }
        viewBinding.answerChildChoice5.apply {
            visibility = if (answerMessages[4].isNotEmpty()) View.VISIBLE else View.GONE
            text = answerMessages[4]
        }

        // 前回タップされた位置を復元
        viewBinding.answerChildChoiceRadioGroup.check(cacheAnswerArray[position])

        // RadioButtonタップ時の処理
        viewBinding.listener = object : QuestionnaireAnswerFragmentListener {
            override fun onChangeAnswer(radioGroup: RadioGroup, id: Int) {
                val checkedButton = viewBinding.root.findViewById<RadioButton>(id)
                val answerNumber = checkedButton.tag.toString().toInt()
                checkCallbackListener(position, id, answerNumber)
            }
        }
    }
}
