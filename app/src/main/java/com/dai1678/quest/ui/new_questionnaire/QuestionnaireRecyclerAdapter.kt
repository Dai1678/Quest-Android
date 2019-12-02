package com.dai1678.quest.ui.new_questionnaire

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.dai1678.quest.databinding.ListItemQuestionnaireChildChoiceBinding
import com.dai1678.quest.ui.new_questionnaire.QuestionnaireRecyclerAdapter.QuestionnaireViewHolder

class QuestionnaireRecyclerAdapter(
    context: Context,
    private val viewModel: QuestionnaireAnswerViewModel,
    private val parentLifecycleOwner: LifecycleOwner
) :
    RecyclerView.Adapter<QuestionnaireViewHolder>() {
    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionnaireViewHolder {
        val binding = ListItemQuestionnaireChildChoiceBinding.inflate(inflater, parent, false)

        return QuestionnaireViewHolder(binding)
    }

    override fun getItemCount(): Int = viewModel.questionSize

    override fun onBindViewHolder(holder: QuestionnaireViewHolder, position: Int) {
        holder.binding.viewModel = viewModel
        holder.binding.position = position
        holder.binding.lifecycleOwner = parentLifecycleOwner
    }

    inner class QuestionnaireViewHolder(val binding: ListItemQuestionnaireChildChoiceBinding) :
        RecyclerView.ViewHolder(binding.root)
}
