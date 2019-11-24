package com.dai1678.quest.ui.questionnaire

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.dai1678.quest.R
import com.dai1678.quest.databinding.FragmentQuestionnaireStartBinding

class QuestionnaireStartFragment : Fragment() {

    private val viewModel: QuestionnaireViewModel by activityViewModels()
    private lateinit var binding: FragmentQuestionnaireStartBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_questionnaire_start, container, false
        )
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        binding.questionnaireTopNextButton.setOnClickListener {
            viewModel.nextPage()
            findNavController().navigate(
                R.id.action_to_questionnaireFragment
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.questionnaire_menu, menu)
    }
}
