package com.dai1678.quest.ui.questionnaire

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.recyclerview.widget.RecyclerView
import com.dai1678.quest.databinding.ListItemQuestionnaireChildChoiceBinding
import com.dai1678.quest.enums.Answer
import com.dai1678.quest.enums.Question
import com.dai1678.quest.listener.QuestionnaireAnswerFragmentListener
import com.dai1678.quest.ui.questionnaire.QuestionnaireRecyclerAdapter.QuestionnaireViewHolder

/**
 * 小問の表示に利用しているRecyclerViewのAdapter
 */
class QuestionnaireRecyclerAdapter(
    private val context: Context,
    private val question: Question,
    private val answer: Answer,
    private val cacheAnswerArray: IntArray,
    private val checkCallbackListener: (Int, Int, Int) -> Unit
) :
    RecyclerView.Adapter<QuestionnaireViewHolder>() {
    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionnaireViewHolder {
        val binding = ListItemQuestionnaireChildChoiceBinding.inflate(inflater, parent, false)

        return QuestionnaireViewHolder(binding)
    }

    override fun getItemCount(): Int = question.size

    override fun onBindViewHolder(holder: QuestionnaireViewHolder, position: Int) {
        holder.binding.questionnaireSubNumberText.text =
            question.getSubTitleNumbers(context)[position]
        holder.binding.questionnaireSubMessageText.text = question.getSubMessages(context)[position]

        val answerMessages = answer.getAnswers(context)
        holder.binding.answerChildChoice1.text = answerMessages[0]
        holder.binding.answerChildChoice2.text = answerMessages[1]
        holder.binding.answerChildChoice3.text = answerMessages[2]
        holder.binding.answerChildChoice4.apply {
            visibility = if (answerMessages[3].isNotEmpty()) View.VISIBLE else View.GONE
            text = answerMessages[3]
        }
        holder.binding.answerChildChoice5.apply {
            visibility = if (answerMessages[4].isNotEmpty()) View.VISIBLE else View.GONE
            text = answerMessages[4]
        }

        // 前回タップされた位置を復元
        holder.binding.answerChildChoiceRadioGroup.check(cacheAnswerArray[position])

        // RadioButtonタップ時の処理
        holder.binding.listener = object : QuestionnaireAnswerFragmentListener {
            override fun onChangeAnswer(radioGroup: RadioGroup, id: Int) {
                val checkedButton = holder.binding.root.findViewById<RadioButton>(id)
                val answerNumber = checkedButton.tag.toString().toInt()
                checkCallbackListener(position, id, answerNumber)
            }
        }
    }

    inner class QuestionnaireViewHolder(val binding: ListItemQuestionnaireChildChoiceBinding) :
        RecyclerView.ViewHolder(binding.root)
}
