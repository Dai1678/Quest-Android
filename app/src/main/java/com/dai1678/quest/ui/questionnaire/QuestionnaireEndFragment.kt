package com.dai1678.quest.ui.questionnaire

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.dai1678.quest.R
import com.dai1678.quest.databinding.FragmentQuestionnaireEndBinding
import com.dai1678.quest.entity.QuestionnaireResult

class QuestionnaireEndFragment : Fragment() {

    private val viewModel: QuestionnaireViewModel by activityViewModels()
    private lateinit var binding: FragmentQuestionnaireEndBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
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

        viewModel.response.observe(viewLifecycleOwner, Observer {
            Log.d("response", it.message)
        })

        val navController = findNavController()

        binding.questionnaireEndBackButton.setOnClickListener {
            navController.popBackStack()
        }

        binding.questionnaireEndSubmitButton.setOnClickListener {
            submitResult()
            requireActivity().finish()
        }
    }

    // TODO アンケート結果送信
    private fun submitResult() {
        Log.d("answer", formatResult().toString())
        val questionnaireActivity = activity as QuestionnaireActivity
        viewModel.submitQuestionnaire(formatResult(), questionnaireActivity.patientId)
    }

    private fun formatResult(): QuestionnaireResult {
        val answerTexts = viewModel.selectRadioButtonTexts.toList()

        return QuestionnaireResult(
            answer1 = answerTexts[0],
            answer2 = answerTexts[1],
            answer3 = answerTexts.subList(2, 11),
            answer4 = answerTexts.subList(12, 15),
            answer5 = answerTexts.subList(16, 18),
            answer6 = answerTexts[19],
            answer7 = answerTexts[20],
            answer8 = answerTexts[21],
            answer9 = answerTexts.subList(22, 30),
            answer10 = answerTexts[31],
            answer11 = answerTexts.subList(32, 35)
        )
    }
}
