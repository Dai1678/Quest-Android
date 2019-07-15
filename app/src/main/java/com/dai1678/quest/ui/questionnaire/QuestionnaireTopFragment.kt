package com.dai1678.quest.ui.questionnaire

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.dai1678.quest.R
import com.dai1678.quest.databinding.FragmentQuestionnaireTopBinding

class QuestionnaireTopFragment : Fragment() {

    private lateinit var binding: FragmentQuestionnaireTopBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_questionnaire_top, container, false
        )
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        binding.questionnaireTopToolbar.apply {
            setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
            setNavigationOnClickListener {
                navController.navigate(
                    R.id.action_questionnaireTopFragment_to_suspendCheckDialogFragment
                )
            }
            inflateMenu(R.menu.questionnaire_menu)
            setOnMenuItemClickListener {
                it.onNavDestinationSelected(navController)
            }
        }

        binding.questionnaireTopNextButton.setOnClickListener {
            // TODO アンケート画面問1へNavigation
        }
    }
}
