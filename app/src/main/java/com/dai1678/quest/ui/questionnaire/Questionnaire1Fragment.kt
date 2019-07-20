package com.dai1678.quest.ui.questionnaire

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dai1678.quest.R
import com.dai1678.quest.databinding.Questionnaire1FragmentBinding

class Questionnaire1Fragment : Fragment() {

    private val viewModel: QuestionnaireViewModel by viewModels()
    private lateinit var binding: Questionnaire1FragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.questionnaire_1_fragment, container, false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.questionnaire1NumberText.text =
            resources.getString(R.string.questionnaire_1_number)

        binding.questionnaire1MessageText.text =
            resources.getString(R.string.questionnaire_1_message)

        binding.questionnaire1BackButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.questionnaire1NextButton.setOnClickListener {
            // TODO Questionnaire2Fragmentにnavigate
        }
    }
}
