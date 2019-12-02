package com.dai1678.quest.ui.new_questionnaire

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dai1678.quest.databinding.FragmentQuestionnaireSimpleAnswerBinding

class QuestionnaireSimpleAnswerFragment : Fragment() {

    private lateinit var binding: FragmentQuestionnaireSimpleAnswerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuestionnaireSimpleAnswerBinding.inflate(inflater, container, false)
        return binding.root
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
