package com.dai1678.quest.ui.new_questionnaire

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dai1678.quest.databinding.FragmentQuestionnaireBinding

class QuestionnaireChildAnswerFragment : Fragment() {

    private val questionnaireAnswerViewModel: QuestionnaireAnswerViewModel by viewModels({
        requireParentFragment()
    })

    private lateinit var binding: FragmentQuestionnaireBinding

    private var currentPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        currentPage = arguments?.getInt(KEY_PAGE) ?: 0
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuestionnaireBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@QuestionnaireChildAnswerFragment
            page = currentPage
            viewModel = questionnaireAnswerViewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val questionnaireRecyclerAdapter = QuestionnaireRecyclerAdapter(
            requireContext(),
            currentPage,
            questionnaireAnswerViewModel,
            this
        )

        binding.questionnaireRecyclerView.apply {
            adapter = questionnaireRecyclerAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    companion object {

        private const val KEY_PAGE = "page"

        fun newInstance(page: Int): Fragment {
            return QuestionnaireChildAnswerFragment().apply {
                arguments = Bundle().apply {
                    putInt(KEY_PAGE, page)
                }
            }
        }
    }
}
