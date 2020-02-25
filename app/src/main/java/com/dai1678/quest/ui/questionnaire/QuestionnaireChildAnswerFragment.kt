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
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.databinding.GroupieViewHolder

/**
 * 回答画面 小問を含む画面のUIコントローラ
 */
class QuestionnaireChildAnswerFragment : Fragment() {

    private val answerViewModel: QuestionnaireAnswerViewModel by viewModels({
        requireParentFragment()
    })

    private val pagerViewModel: QuestionnairePagerViewModel by viewModels({
        requireParentFragment()
    })

    private lateinit var binding: FragmentQuestionnaireBinding

    // タップされたRadioButtonのidをキャッシュしておく
    private var cacheAnswerId: IntArray = intArrayOf(
        R.id.answer_child_choice_1,
        R.id.answer_child_choice_1,
        R.id.answer_child_choice_1,
        R.id.answer_child_choice_1,
        R.id.answer_child_choice_1
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cacheAnswerId = savedInstanceState?.getIntArray(KEY_CHILD_ANSWER) ?: return
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

    /**
     * 画面回転時にRadioButtonのタップ位置をキャッシュ
     * @param outState Bundle
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putIntArray(KEY_CHILD_ANSWER, cacheAnswerId)
    }

    /**
     * ページをsubscribeしてからRecyclerAdapterを設定する
     * @param context Context
     * @param page 表示している回答画面のページ番号
     * @param question Question Enum
     * @param answer Answer Enum
     */
    private fun initAdapter(context: Context, page: Int, question: Question, answer: Answer) {
        val groupAdapter = GroupAdapter<GroupieViewHolder<*>>()

        groupAdapter.clear()
        repeat(question.size) {
            groupAdapter.add(
                QuestionnaireChildItem(
                    context,
                    question,
                    answer,
                    cacheAnswerId
                ) { position, checkedButtonId, answerNumber ->
                    answerViewModel.setQuestionnaireResult(page, answerNumber, position)
                    cacheAnswerId[position] = checkedButtonId
                }
            )
        }

        binding.questionnaireRecyclerView.apply {
            adapter = groupAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    companion object {
        private const val KEY_CHILD_ANSWER = "answer"

        fun newInstance() = QuestionnaireChildAnswerFragment()
    }
}
