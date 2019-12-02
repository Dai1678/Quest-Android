package com.dai1678.quest.ui.new_questionnaire

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dai1678.quest.databinding.FragmentQuestionnaireSimpleAnswerBinding

class QuestionnaireSimpleAnswerFragment : Fragment() {

    private val questionnaireSimpleAnswerViewModel: QuestionnaireSimpleAnswerViewModel by viewModels()
    private lateinit var binding: FragmentQuestionnaireSimpleAnswerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            FragmentQuestionnaireSimpleAnswerBinding.inflate(inflater, container, false).apply {
                lifecycleOwner = this@QuestionnaireSimpleAnswerFragment
                viewModel = questionnaireSimpleAnswerViewModel
            }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val page = arguments?.getInt(KEY_PAGE) ?: 1
        questionnaireSimpleAnswerViewModel.setQuestionInfo(page)
    }

    companion object {

        private const val KEY_PAGE = "page"

        fun newInstance(page: Int): Fragment {
            return QuestionnaireSimpleAnswerFragment().apply {
                arguments = Bundle().apply {
                    putInt(KEY_PAGE, page)
                }
            }
        }
    }
}
