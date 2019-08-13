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
import com.dai1678.quest.databinding.CardViewQuestionnaire5Binding
import com.dai1678.quest.databinding.FragmentQuestionnaire5Binding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.databinding.ViewHolder

class Questionnaire5Fragment : Fragment() {

    private val viewModel: QuestionnaireViewModel by activityViewModels()
    private val groupAdapter = GroupAdapter<ViewHolder<*>>()
    private var answerIds = arrayOfNulls<Int>(3)

    private lateinit var binding: FragmentQuestionnaire5Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_questionnaire5, container, false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val questionnaireNumbers = resources.getStringArray(R.array.questionnaire_sub_numbers)
        val questionnaireMessages = resources.getStringArray(R.array.questionnaire_5_sub_messages)

        for (i in answerIds.indices) {
            viewModel.selectRadioButtonIds[i + 16]?.let {
                answerIds[i] = it
            }
        }

        val items = ArrayList<CardViewItem>()
        for (i in questionnaireMessages.indices)
            items.add(CardViewItem(questionnaireNumbers[i], questionnaireMessages[i]))
        groupAdapter.update(items)

        binding.questionnaire5RecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = groupAdapter
        }

        val navController = findNavController()

        binding.questionnaire5BackButton.setOnClickListener {
            navController.popBackStack()
        }

        binding.questionnaire5NextButton.setOnClickListener {

            for (i in answerIds.indices) {
                viewModel.selectRadioButtonIds[i + 16] = answerIds[i]
            }

            navController.navigate(R.id.action_questionnaire5Fragment_to_questionnaire6Fragment)
        }
    }

    inner class CardViewItem(
        private val questionnaireNumber: String,
        private val questionnaireMessage: String
    ) : BindableItem<CardViewQuestionnaire5Binding>() {

        override fun getLayout(): Int = R.layout.card_view_questionnaire_5

        override fun bind(viewBinding: CardViewQuestionnaire5Binding, position: Int) {
            viewBinding.apply {
                questionnaire5SubNumberText.text = questionnaireNumber
                questionnaire5SubMessageText.text = questionnaireMessage

                answerIds[position]?.let { questionnaire5RadioGroup.check(it) }

                questionnaire5RadioGroup.setOnCheckedChangeListener { _, id ->
                    answerIds[position] = id
                }
            }
        }
    }
}
