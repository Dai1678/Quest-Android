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
import com.dai1678.quest.databinding.CardViewQuestionnaire3Binding
import com.dai1678.quest.databinding.FragmentQuestionnaire3Binding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.databinding.ViewHolder

class Questionnaire3Fragment : Fragment() {

    private val viewModel: QuestionnaireViewModel by activityViewModels()
    private val groupAdapter = GroupAdapter<ViewHolder<*>>()

    private lateinit var binding: FragmentQuestionnaire3Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_questionnaire3, container, false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val questionnaireNumbers = resources.getStringArray(R.array.questionnaire_sub_numbers)
        val questionnaireMessages = resources.getStringArray(R.array.questionnaire_3_sub_messages)
        val section = Section()
        for (i in questionnaireMessages.indices)
            section.add(CardViewItem(questionnaireNumbers[i], questionnaireMessages[i]))
        groupAdapter.add(section)

        binding.questionnaire3RecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = groupAdapter
        }

        val navController = findNavController()

        binding.questionnaire3BackButton.setOnClickListener {
            navController.popBackStack()
        }

        binding.questionnaire3NextButton.setOnClickListener {
            // TODO Questionnaire4Fragmentにnavigate
        }
    }

    inner class CardViewItem(private val questionnaireNumber: String, private val questionnaireMessage: String) :
        BindableItem<CardViewQuestionnaire3Binding>() {

        override fun getLayout(): Int = R.layout.card_view_questionnaire_3

        override fun bind(viewBinding: CardViewQuestionnaire3Binding, position: Int) {
            viewBinding.apply {
                questionnaire3SubNumberText.text = questionnaireNumber
                questionnaire3SubMessageText.text = questionnaireMessage
                questionnaire3ExpandableLayout.isExpanded = position == 0

                questionnaire3CardViewActionButton.apply {
                    if (questionnaire3ExpandableLayout.isExpanded) {
                        this.text = resources.getString(R.string.questionnaire_collapsed_text)
                    } else {
                        this.text = resources.getString(R.string.questionnaire_expand_text)
                    }

                    setOnClickListener {
                        if (questionnaire3ExpandableLayout.isExpanded) {
                            questionnaire3ExpandableLayout.isExpanded = false
                            this.text = resources.getString(R.string.questionnaire_expand_text)
                        } else {
                            questionnaire3ExpandableLayout.isExpanded = true
                            this.text = resources.getString(R.string.questionnaire_collapsed_text)
                        }
                    }
                }
            }
        }
    }
}
