package com.dai1678.quest.ui.questionnaire

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.dai1678.quest.R
import com.dai1678.quest.databinding.CardViewQuestionnaire9Binding
import com.dai1678.quest.databinding.FragmentQuestionnaire9Binding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.databinding.ViewHolder

class Questionnaire9Fragment : Fragment() {

    private val viewModel: QuestionnaireViewModel by activityViewModels()
    private val groupAdapter = GroupAdapter<ViewHolder<*>>()

    private lateinit var binding: FragmentQuestionnaire9Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_questionnaire9, container, false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val questionnaireNumbers = resources.getStringArray(R.array.questionnaire_sub_numbers)
        val questionnaireMessages = resources.getStringArray(R.array.questionnaire_9_sub_messages)

        val items = ArrayList<CardViewItem>()
        for (i in questionnaireMessages.indices)
            items.add(CardViewItem(questionnaireNumbers[i], questionnaireMessages[i]))
        groupAdapter.update(items)

        binding.questionnaire9RecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = groupAdapter
        }

        val navController = findNavController()

        binding.questionnaire9BackButton.setOnClickListener {
            navController.popBackStack()
        }

        binding.questionnaire9NextButton.setOnClickListener {
            navController.navigate(R.id.action_questionnaire9Fragment_to_questionnaire10Fragment)
        }
    }

    inner class CardViewItem(
        private val questionnaireNumber: String,
        private val questionnaireMessage: String
    ) : BindableItem<CardViewQuestionnaire9Binding>() {

        override fun getLayout(): Int = R.layout.card_view_questionnaire_9

        override fun bind(viewBinding: CardViewQuestionnaire9Binding, position: Int) {
            viewBinding.apply {
                questionnaire9SubNumberText.text = questionnaireNumber
                questionnaire9SubMessageText.text = questionnaireMessage
                questionnaire9ExpandableLayout.isExpanded = position == 0

                questionnaire9CardViewActionButton.apply {
                    if (questionnaire9ExpandableLayout.isExpanded) {
                        this.text = resources.getString(R.string.questionnaire_collapsed_text)
                    } else {
                        this.text = resources.getString(R.string.questionnaire_expand_text)
                    }

                    setOnClickListener {
                        if (questionnaire9ExpandableLayout.isExpanded) {
                            questionnaire9ExpandableLayout.isExpanded = false
                            this.text = resources.getString(R.string.questionnaire_expand_text)
                        } else {
                            questionnaire9ExpandableLayout.isExpanded = true
                            this.text = resources.getString(R.string.questionnaire_collapsed_text)
                        }
                    }
                }
            }
        }
    }
}
