package com.dai1678.quest.ui.questionnaire

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dai1678.quest.R
import com.dai1678.quest.databinding.CardViewQuestionnaire4Binding
import com.dai1678.quest.databinding.FragmentQuestionnaire4Binding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.databinding.ViewHolder

class Questionnaire4Fragment : Fragment() {

    private val viewModel: QuestionnaireViewModel by activityViewModels()
    private val groupAdapter = GroupAdapter<ViewHolder<*>>()
    private var answerIds = arrayOfNulls<Int>(4)

    private lateinit var binding: FragmentQuestionnaire4Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_questionnaire4, container, false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val questionnaireNumbers = resources.getStringArray(R.array.questionnaire_sub_numbers)
        val questionnaireMessages = resources.getStringArray(R.array.questionnaire_4_sub_messages)

        for (i in answerIds.indices) {
            viewModel.selectRadioButtonIds[i + 12]?.let {
                answerIds[i] = it
            }
        }

        val items = ArrayList<CardViewItem>()
        for (i in questionnaireMessages.indices)
            items.add(CardViewItem(questionnaireNumbers[i], questionnaireMessages[i]))
        groupAdapter.update(items)

        binding.questionnaire4RecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = groupAdapter
        }

        val navController = findNavController()

        binding.questionnaire4BackButton.setOnClickListener {
            navController.popBackStack()
        }

        binding.questionnaire4NextButton.setOnClickListener {

            for (i in answerIds.indices) {
                viewModel.selectRadioButtonIds[i + 12] = answerIds[i]
                answerIds[i]?.let {
                    viewModel.selectRadioButtonTexts[i + 12] =
                        view.findViewById<RadioButton>(it).text.toString()
                }
            }

            navController.navigate(R.id.action_questionnaire4Fragment_to_questionnaire5Fragment)
        }
    }

    inner class CardViewItem(
        private val questionnaireNumber: String,
        private val questionnaireMessage: String
    ) : BindableItem<CardViewQuestionnaire4Binding>() {

        override fun getLayout(): Int = R.layout.card_view_questionnaire_4

        override fun bind(viewBinding: CardViewQuestionnaire4Binding, position: Int) {
            viewBinding.apply {
                questionnaire4SubNumberText.text = questionnaireNumber
                questionnaire4SubMessageText.text = questionnaireMessage

                answerIds[position]?.let { questionnaire4RadioGroup.check(it) }
                answerIds[position] = questionnaire4RadioGroup.checkedRadioButtonId

                questionnaire4RadioGroup.setOnCheckedChangeListener { _, id ->
                    answerIds[position] = id
                }
            }
        }
    }
}
