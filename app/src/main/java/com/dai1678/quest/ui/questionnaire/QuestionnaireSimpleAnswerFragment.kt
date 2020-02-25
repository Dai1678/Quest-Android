package com.dai1678.quest.ui.questionnaire

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.dai1678.quest.databinding.FragmentQuestionnaireSimpleAnswerBinding
import com.dai1678.quest.enums.Answer
import com.dai1678.quest.enums.Question
import com.dai1678.quest.listener.QuestionnaireAnswerFragmentListener

/**
 * 回答画面 小問を含まない画面のUIコントローラ
 */
class QuestionnaireSimpleAnswerFragment : Fragment() {

    private val viewModel: QuestionnaireAnswerViewModel by viewModels({
        requireParentFragment()
    })
    private val pagerViewModel: QuestionnairePagerViewModel by viewModels({
        requireParentFragment()
    })
    private lateinit var binding: FragmentQuestionnaireSimpleAnswerBinding

    private val listener = object : QuestionnaireAnswerFragmentListener {
        override fun onChangeAnswer(radioGroup: RadioGroup, id: Int) {
            val checkedButton = binding.root.findViewById<RadioButton>(id)
            val answerNumber = checkedButton.tag.toString().toInt()
            viewModel.setQuestionnaireResult(pagerViewModel.currentPage.value ?: 0, answerNumber)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            FragmentQuestionnaireSimpleAnswerBinding.inflate(inflater, container, false).apply {
                lifecycleOwner = this@QuestionnaireSimpleAnswerFragment
                viewModel = this@QuestionnaireSimpleAnswerFragment.viewModel
                listener = this@QuestionnaireSimpleAnswerFragment.listener
            }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pagerViewModel.currentPage.observe(viewLifecycleOwner) { page ->
            val context = context ?: return@observe
            val question = Question.valueOf(page)
            val answer = Answer.valueOf(page)
            viewModel.update(question.getMessage(context), answer.getAnswers(context))
        }
    }

    companion object {
        fun newInstance(): Fragment = QuestionnaireSimpleAnswerFragment()
    }
}
