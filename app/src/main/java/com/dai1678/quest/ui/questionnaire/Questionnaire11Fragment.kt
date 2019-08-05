package com.dai1678.quest.ui.questionnaire

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dai1678.quest.R
import com.dai1678.quest.databinding.CardViewQuestionnaire11Binding
import com.dai1678.quest.databinding.FragmentQuestionnaire11Binding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.databinding.ViewHolder

class Questionnaire11Fragment : Fragment() {

    private val viewModel: QuestionnaireViewModel by activityViewModels()
    private val groupAdapter = GroupAdapter<ViewHolder<*>>()

    private lateinit var binding: FragmentQuestionnaire11Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_questionnaire11, container, false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val questionnaireNumbers = resources.getStringArray(R.array.questionnaire_sub_numbers)
        val questionnaireMessages = resources.getStringArray(R.array.questionnaire_11_sub_messages)

        val items = ArrayList<CardViewItem>()
        for (i in questionnaireMessages.indices)
            items.add(CardViewItem(questionnaireNumbers[i], questionnaireMessages[i]))
        groupAdapter.update(items)

        binding.questionnaire11RecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = groupAdapter
        }

        val navController = findNavController()

        binding.questionnaire11BackButton.setOnClickListener {
            navController.popBackStack()
        }

        binding.questionnaire11NextButton.setOnClickListener {
            navController.navigate(R.id.action_questionnaire11Fragment_to_questionnaireEndFragment)
        }
    }

    inner class CardViewItem(
        private val questionnaireNumber: String,
        private val questionnaireMessage: String
    ) : BindableItem<CardViewQuestionnaire11Binding>() {

        override fun getLayout(): Int = R.layout.card_view_questionnaire_11

        override fun bind(viewBinding: CardViewQuestionnaire11Binding, position: Int) {
            viewBinding.apply {
                questionnaire11SubNumberText.text = questionnaireNumber
                questionnaire11SubMessageText.text = questionnaireMessage
                questionnaire11ExpandableLayout.isExpanded = position == 0

                questionnaire11CardViewActionButton.apply {
                    if (questionnaire11ExpandableLayout.isExpanded) {
                        this.text = resources.getString(R.string.questionnaire_collapsed_text)
                    } else {
                        this.text = resources.getString(R.string.questionnaire_expand_text)
                    }

                    setOnClickListener {
                        if (questionnaire11ExpandableLayout.isExpanded) {
                            questionnaire11ExpandableLayout.isExpanded = false
                            this.text = resources.getString(R.string.questionnaire_expand_text)
                        } else {
                            questionnaire11ExpandableLayout.isExpanded = true
                            this.text = resources.getString(R.string.questionnaire_collapsed_text)
                        }
                    }
                }
            }
        }
    }
}
