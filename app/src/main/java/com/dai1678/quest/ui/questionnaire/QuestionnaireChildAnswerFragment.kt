package com.dai1678.quest.ui.questionnaire

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.dai1678.quest.R
import com.dai1678.quest.databinding.FragmentQuestionnaireBinding
import com.dai1678.quest.enums.Answer
import com.dai1678.quest.enums.Question
import com.dai1678.quest.model.PatientDetail

/**
 * 小問を含む回答画面のFragment
 */
class QuestionnaireChildAnswerFragment : Fragment() {

    private val answerViewModel: QuestionnaireAnswerViewModel by viewModels({
        requireParentFragment()
    })

    private val pagerViewModel: QuestionnairePagerViewModel by viewModels({
        requireParentFragment()
    })

    private lateinit var binding: FragmentQuestionnaireBinding

    private var questionnaireCacheAnswerArray: IntArray = intArrayOf(
        R.id.answer_child_choice_1,
        R.id.answer_child_choice_1,
        R.id.answer_child_choice_1,
        R.id.answer_child_choice_1,
        R.id.answer_child_choice_1
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        questionnaireCacheAnswerArray =
            savedInstanceState?.getIntArray(KEY_CHILD_ANSWER) ?: return
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuestionnaireBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@QuestionnaireChildAnswerFragment
            viewModel = answerViewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pagerViewModel.currentPage.observe(viewLifecycleOwner) { page ->
            val context = context ?: return@observe
            val question = Question.valueOf(page)
            val answer = Answer.valueOf(page)
            initAdapter(context, page, question, answer)
            answerViewModel.update(
                question.getMessage(context),
                answer.getAnswers(context)
            )
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putIntArray(KEY_CHILD_ANSWER, questionnaireCacheAnswerArray)
    }

    // ページをsubscribeしてからRecyclerAdapterを設定する
    private fun initAdapter(context: Context, page: Int, question: Question, answer: Answer) {
        val questionnaireRecyclerAdapter =
            QuestionnaireRecyclerAdapter(
                context,
                question,
                answer,
                page,
                questionnaireCacheAnswerArray,
                answerViewModel
            ) { position, checkedButtonId ->
                questionnaireCacheAnswerArray[position] = checkedButtonId
            }

        binding.questionnaireRecyclerView.apply {
            adapter = questionnaireRecyclerAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    companion object {

        private const val KEY_CHILD_ANSWER = "answer"
        private const val KEY_PATIENT_DETAIL = "patient"

        fun newInstance(patientDetail: PatientDetail): Fragment {
            return QuestionnaireChildAnswerFragment()
                .apply {
                    arguments = Bundle().apply {
                        putParcelable(KEY_PATIENT_DETAIL, patientDetail)
                    }
                }
        }
    }
}
