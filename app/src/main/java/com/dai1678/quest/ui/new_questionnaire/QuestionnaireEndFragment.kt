package com.dai1678.quest.ui.new_questionnaire

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.dai1678.quest.R
import com.dai1678.quest.databinding.FragmentQuestionnaireEndBinding
import com.dai1678.quest.entity.QuestionnaireResult
import com.dai1678.quest.listener.QuestionnaireEndFragmentListener
import com.dai1678.quest.ui.questionnaire.QuestionnaireViewModel
import com.google.android.material.snackbar.Snackbar

class QuestionnaireEndFragment : Fragment() {

    private val questionnaireViewModel: QuestionnaireViewModel by viewModels()

    private val questionnaireEndFragmentListener = object : QuestionnaireEndFragmentListener {
        override fun onClickSendAnswer(view: View) {
            Log.d("answer", formatResult().toString())
            questionnaireViewModel.submitQuestionnaire(formatResult())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentQuestionnaireEndBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@QuestionnaireEndFragment
            viewModel = questionnaireViewModel
            listener = questionnaireEndFragmentListener
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        questionnaireViewModel.getSnackBarAction().observe(viewLifecycleOwner) {
            Snackbar.make(view, it.text, Snackbar.LENGTH_LONG).apply {
                getView().setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
                getView().findViewById<TextView>(
                    com.google.android.material.R.id.snackbar_text
                ).apply {
                    setTextColor(Color.WHITE)
                }
            }.show()
        }

        questionnaireViewModel.getResponse().observe(viewLifecycleOwner) {
            requireActivity().finish()
        }
    }

    // TODO リファクタリング
    private fun formatResult(): QuestionnaireResult {
        val answerTexts = questionnaireViewModel.selectRadioButtonTexts.toList()

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
