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
import com.dai1678.quest.databinding.FragmentQuestionnaire7Binding

class Questionnaire7Fragment : Fragment() {

    private val viewModel: QuestionnaireViewModel by activityViewModels()
    private lateinit var binding: FragmentQuestionnaire7Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_questionnaire7, container, false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.selectRadioButtonIds[20]?.let {
            binding.questionnaire7RadioGroup.check(it)
        }

        val navController = findNavController()

        binding.questionnaire7BackButton.setOnClickListener {
            navController.popBackStack()
        }

        binding.questionnaire7NextButton.setOnClickListener {
            val checkId = binding.questionnaire7RadioGroup.checkedRadioButtonId
            viewModel.selectRadioButtonIds[20] = checkId
            viewModel.selectRadioButtonTexts[20] =
                view.findViewById<RadioButton>(checkId).text.toString()

            navController.navigate(
                R.id.action_questionnaire7Fragment_to_questionnaire8Fragment
            )
        }
    }
}