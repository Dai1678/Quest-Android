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
import com.dai1678.quest.databinding.CardViewQuestionnaire11Binding
import com.dai1678.quest.databinding.FragmentQuestionnaire11Binding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.databinding.ViewHolder

class Questionnaire11Fragment : Fragment() {

    private val viewModel: QuestionnaireViewModel by activityViewModels()
    private val groupAdapter = GroupAdapter<ViewHolder<*>>()
    private var answerIds = arrayOfNulls<Int>(4)

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

        for (i in answerIds.indices) {
            viewModel.selectRadioButtonIds[i + 32]?.let {
                answerIds[i] = it
            }
        }

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

            for (i in answerIds.indices) {
                viewModel.selectRadioButtonIds[i + 32] = answerIds[i]
                answerIds[i]?.let {
                    viewModel.selectRadioButtonTexts[i + 32] =
                        view.findViewById<RadioButton>(it).text.toString()
                }
            }

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

                answerIds[position]?.let { questionnaire11RadioGroup.check(it) }
                answerIds[position] = questionnaire11RadioGroup.checkedRadioButtonId

                questionnaire11RadioGroup.setOnCheckedChangeListener { _, id ->
                    answerIds[position] = id
                }
            }
        }
    }
}
