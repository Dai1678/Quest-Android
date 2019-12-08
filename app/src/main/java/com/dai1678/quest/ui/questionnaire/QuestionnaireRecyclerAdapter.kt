package com.dai1678.quest.ui.questionnaire

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.dai1678.quest.R
import com.dai1678.quest.databinding.ListItemQuestionnaireChildChoiceBinding
import com.dai1678.quest.entity.QuestionSize
import com.dai1678.quest.listener.QuestionnaireAnswerFragmentListener
import com.dai1678.quest.ui.questionnaire.QuestionnaireRecyclerAdapter.QuestionnaireViewHolder

class QuestionnaireRecyclerAdapter(
    private val context: Context,
    private val currentPage: Int,
    private val cacheAnswerArray: IntArray,
    private val viewModel: QuestionnaireAnswerViewModel,
    private val parentLifecycleOwner: LifecycleOwner,
    private val checkCallbackListener: (Int, Int) -> Unit
) :
    RecyclerView.Adapter<QuestionnaireViewHolder>() {
    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionnaireViewHolder {
        val binding = ListItemQuestionnaireChildChoiceBinding.inflate(inflater, parent, false)

        return QuestionnaireViewHolder(binding)
    }

    override fun getItemCount(): Int = when (currentPage) {
        3 -> QuestionSize.PAGE3.size
        4 -> QuestionSize.PAGE4.size
        5 -> QuestionSize.PAGE5.size
        6 -> QuestionSize.PAGE6.size
        10 -> QuestionSize.PAGE10.size
        11 -> QuestionSize.PAGE11.size
        13 -> QuestionSize.PAGE13.size
        else -> 0
    }

    override fun onBindViewHolder(holder: QuestionnaireViewHolder, position: Int) {
        holder.binding.viewModel = viewModel
        holder.binding.position = position
        holder.binding.page = currentPage
        holder.binding.lifecycleOwner = parentLifecycleOwner

        val resources = context.resources
        val labels: List<String> = when (currentPage) {
            3 -> resources.getStringArray(R.array.questionnaire_sub_first_half_numbers)
            4 -> resources.getStringArray(R.array.questionnaire_sub_latter_half_numbers)
            5 -> resources.getStringArray(R.array.questionnaire_sub_first_half_numbers)
            6 -> resources.getStringArray(R.array.questionnaire_sub_first_half_numbers)
            10 -> resources.getStringArray(R.array.questionnaire_sub_first_half_numbers)
            11 -> resources.getStringArray(R.array.questionnaire_sub_latter_half_numbers)
            13 -> resources.getStringArray(R.array.questionnaire_sub_first_half_numbers)
            else -> return
        }.toList()
        holder.binding.numberLabels = labels

        val messages: List<String> = when (currentPage) {
            3 -> resources.getStringArray(R.array.questionnaire_3_1_sub_messages)
            4 -> resources.getStringArray(R.array.questionnaire_3_2_sub_messages)
            5 -> resources.getStringArray(R.array.questionnaire_4_sub_messages)
            6 -> resources.getStringArray(R.array.questionnaire_5_sub_messages)
            10 -> resources.getStringArray(R.array.questionnaire_9_1_sub_messages)
            11 -> resources.getStringArray(R.array.questionnaire_9_2_sub_messages)
            13 -> resources.getStringArray(R.array.questionnaire_11_sub_messages)
            else -> return
        }.toList()
        holder.binding.messages = messages

        holder.binding.answerVisible = currentPage != 3 && currentPage != 4

        holder.binding.answerChildChoiceRadioGroup.check(cacheAnswerArray[position])

        holder.binding.listener = object : QuestionnaireAnswerFragmentListener {
            override fun onChangeAnswer(radioGroup: RadioGroup, id: Int) {
                val checkedButton = holder.binding.root.findViewById<RadioButton>(id)
                val answerNumber = checkedButton.tag.toString().toInt()
                viewModel.setQuestionnaireResult(currentPage, position, answerNumber)
                checkCallbackListener(position, id)
            }
        }
    }

    inner class QuestionnaireViewHolder(val binding: ListItemQuestionnaireChildChoiceBinding) :
        RecyclerView.ViewHolder(binding.root)
}
