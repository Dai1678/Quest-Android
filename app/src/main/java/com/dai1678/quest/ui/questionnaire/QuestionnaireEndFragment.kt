package com.dai1678.quest.ui.questionnaire

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.dai1678.quest.R
import com.dai1678.quest.databinding.FragmentQuestionnaireEndBinding
import com.dai1678.quest.entity.QuestionnaireResult
import com.google.android.material.snackbar.Snackbar

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

        viewModel.getSnackBarAction().observe(viewLifecycleOwner) {
            Snackbar.make(view, it.text, Snackbar.LENGTH_LONG).apply {
                getView().setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
                getView().findViewById<TextView>(
                    com.google.android.material.R.id.snackbar_text
                ).apply {
                    setTextColor(Color.WHITE)
                }
            }.show()
        }

        viewModel.getResponse().observe(viewLifecycleOwner) {
            requireActivity().finish()
        }

        val navController = findNavController()

        binding.questionnaireEndBackButton.setOnClickListener {
            viewModel.backPage()
            navController.navigate(R.id.action_global_questionnaire_fragment)
        }

        binding.questionnaireEndSubmitButton.setOnClickListener {
            Log.d("answer", formatResult().toString())
            viewModel.submitQuestionnaire(formatResult())
        }
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
