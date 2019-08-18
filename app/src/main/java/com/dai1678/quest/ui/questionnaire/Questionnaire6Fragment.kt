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
import com.dai1678.quest.R
import com.dai1678.quest.databinding.FragmentQuestionnaire6Binding

class Questionnaire6Fragment : Fragment() {

    private val viewModel: QuestionnaireViewModel by activityViewModels()
    private lateinit var binding: FragmentQuestionnaire6Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_questionnaire6, container, false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.selectRadioButtonIds[19]?.let {
            binding.questionnaire6RadioGroup.check(it)
        }

        val navController = findNavController()

        binding.questionnaire6BackButton.setOnClickListener {
            navController.popBackStack()
        }

        binding.questionnaire6NextButton.setOnClickListener {
            val checkId = binding.questionnaire6RadioGroup.checkedRadioButtonId
            viewModel.selectRadioButtonIds[19] = checkId
            viewModel.selectRadioButtonTexts[19] =
                view.findViewById<RadioButton>(checkId).text.toString()

            navController.navigate(
                R.id.action_questionnaire6Fragment_to_questionnaire7Fragment
            )
        }
    }
}
