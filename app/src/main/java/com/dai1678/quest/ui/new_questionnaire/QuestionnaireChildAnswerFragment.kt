package com.dai1678.quest.ui.new_questionnaire

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dai1678.quest.databinding.FragmentQuestionnaireBinding

class QuestionnaireChildAnswerFragment : Fragment() {

    private lateinit var binding: FragmentQuestionnaireBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuestionnaireBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
