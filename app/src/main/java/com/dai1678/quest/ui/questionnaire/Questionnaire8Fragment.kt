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
import com.dai1678.quest.databinding.FragmentQuestionnaire8Binding

class Questionnaire8Fragment : Fragment() {

    private val viewModel: QuestionnaireViewModel by activityViewModels()
    private lateinit var binding: FragmentQuestionnaire8Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_questionnaire8, container, false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.selectRadioButtonIds[21]?.let {
            binding.questionnaire8RadioGroup.check(it)
        }

        val navController = findNavController()

        binding.questionnaire8BackButton.setOnClickListener {
            navController.popBackStack()
        }

        binding.questionnaire8NextButton.setOnClickListener {
            val checkId = binding.questionnaire8RadioGroup.checkedRadioButtonId
            viewModel.selectRadioButtonIds[21] = checkId
            viewModel.selectRadioButtonTexts[21] =
                view.findViewById<RadioButton>(checkId).text.toString()

            navController.navigate(
                R.id.action_questionnaire8Fragment_to_questionnaire9Fragment
            )
        }
    }
}
