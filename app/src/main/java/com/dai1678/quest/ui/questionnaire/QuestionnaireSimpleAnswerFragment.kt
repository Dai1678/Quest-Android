package com.dai1678.quest.ui.questionnaire

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dai1678.quest.databinding.FragmentQuestionnaireSimpleAnswerBinding
import com.dai1678.quest.entity.PatientDetail
import com.dai1678.quest.listener.QuestionnaireAnswerFragmentListener

class QuestionnaireSimpleAnswerFragment : Fragment() {

    private val viewModel: QuestionnaireAnswerViewModel by viewModels({
        requireParentFragment()
    })
    private lateinit var binding: FragmentQuestionnaireSimpleAnswerBinding

    private var currentPage = 0

    private val listener = object : QuestionnaireAnswerFragmentListener {
        override fun onChangeAnswer(radioGroup: RadioGroup, id: Int) {
            val checkedButton = binding.root.findViewById<RadioButton>(id)
            val answerNumber = checkedButton.tag.toString().toInt()
            viewModel.setQuestionnaireResult(currentPage, answerNumber)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentPage = arguments?.getInt(KEY_PAGE) ?: 1
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            FragmentQuestionnaireSimpleAnswerBinding.inflate(inflater, container, false).apply {
                lifecycleOwner = this@QuestionnaireSimpleAnswerFragment
                page = currentPage
                viewModel = this@QuestionnaireSimpleAnswerFragment.viewModel
                listener = this@QuestionnaireSimpleAnswerFragment.listener
            }
        return binding.root
    }

    companion object {
        private const val KEY_PAGE = "page"
        private const val KEY_PATIENT_DETAIL = "patient"

        fun newInstance(page: Int, patientDetail: PatientDetail): Fragment {
            return QuestionnaireSimpleAnswerFragment().apply {
                arguments = Bundle().apply {
                    putInt(KEY_PAGE, page)
                    putParcelable(KEY_PATIENT_DETAIL, patientDetail)
                }
            }
        }
    }
}
