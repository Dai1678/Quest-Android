package com.dai1678.quest.ui.questionnaire


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController

import com.dai1678.quest.R
import com.dai1678.quest.databinding.FragmentQuestionnaireEndBinding

class QuestionnaireEndFragment : Fragment() {

    private val viewModel: QuestionnaireViewModel by activityViewModels()
    private lateinit var binding: FragmentQuestionnaireEndBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_questionnaire_end, container, false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        binding.questionnaireEndBackButton.setOnClickListener {
            navController.popBackStack()
        }

        binding.questionnaireEndSubmitButton.setOnClickListener {


            requireActivity().finish()
        }
    }

    // TODO アンケート結果送信
    private fun submitResult() {

    }

}
