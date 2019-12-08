package com.dai1678.quest.ui.new_questionnaire

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.dai1678.quest.R
import com.dai1678.quest.databinding.FragmentQuestionnaireEndBinding
import com.dai1678.quest.listener.QuestionnaireEndFragmentListener
import com.google.android.material.snackbar.Snackbar

class QuestionnaireEndFragment : Fragment() {

    private val questionnaireAnswerViewModel: QuestionnaireAnswerViewModel by viewModels({
        requireParentFragment()
    })

    private lateinit var binding: FragmentQuestionnaireEndBinding

    private val questionnaireEndFragmentListener = object : QuestionnaireEndFragmentListener {
        override fun onClickSendAnswer(view: View) {
            val patientId = arguments?.getString(KEY_PATIENT_ID) ?: "empty"
            questionnaireAnswerViewModel.sendQuestionnaireResult(patientId)
        }
    }

    private val callbackListener = object : QuestionnaireAnswerViewModel.Callback {
        override fun finishQuestionnaire() {
            findNavController().navigate(R.id.action_global_patient_list_fragment)
        }

        override fun showErrorSnackBar(message: String) {
            Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).apply {
                view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
                view.findViewById<TextView>(
                    com.google.android.material.R.id.snackbar_text
                ).apply {
                    setTextColor(Color.WHITE)
                }
            }.show()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuestionnaireEndBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@QuestionnaireEndFragment
            viewModel = questionnaireAnswerViewModel.apply {
                callback = callbackListener
            }
            listener = questionnaireEndFragmentListener
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("patientId", arguments?.getString(KEY_PATIENT_ID) ?: "empty")
//
//        questionnaireViewModel.getResponse().observe(viewLifecycleOwner) {
//            requireActivity().finish()
//        }
    }

    companion object {

        private const val KEY_PATIENT_ID = "patientId"

        fun newInstance(patientId: String): Fragment {
            return QuestionnaireEndFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_PATIENT_ID, patientId)
                }
            }
        }
    }
}
