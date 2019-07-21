package com.dai1678.quest.ui.questionnaire

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.dai1678.quest.R
import com.dai1678.quest.databinding.FragmentQuestionnaire2Binding

class Questionnaire2Fragment : Fragment() {

    private val viewModel: QuestionnaireViewModel by activityViewModels()
    private lateinit var binding: FragmentQuestionnaire2Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_questionnaire2, container, false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        binding.questionnaire2BackButton.setOnClickListener {
            navController.popBackStack()
        }

        binding.questionnaire2NextButton.setOnClickListener {
            navController.navigate(
                R.id.action_questionnaire2Fragment_to_questionnaire3Fragment
            )
        }
    }
}
