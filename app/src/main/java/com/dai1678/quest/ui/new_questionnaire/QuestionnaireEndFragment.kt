package com.dai1678.quest.ui.new_questionnaire

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dai1678.quest.databinding.FragmentQuestionnaireEndBinding
import com.dai1678.quest.listener.QuestionnaireEndFragmentListener

class QuestionnaireEndFragment : Fragment() {

    private val questionnaireAnswerViewModel: QuestionnaireAnswerViewModel by viewModels({
        requireParentFragment()
    })

    private val questionnaireEndFragmentListener = object : QuestionnaireEndFragmentListener {
        override fun onClickSendAnswer(view: View) {
            questionnaireAnswerViewModel.sendQuestionnaireResult()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentQuestionnaireEndBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@QuestionnaireEndFragment
            viewModel = questionnaireAnswerViewModel
            listener = questionnaireEndFragmentListener
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("patientId", arguments?.getString(KEY_PATIENT_ID) ?: "empty")

//        questionnaireViewModel.getSnackBarAction().observe(viewLifecycleOwner) {
//            Snackbar.make(view, it.text, Snackbar.LENGTH_LONG).apply {
//                getView().setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
//                getView().findViewById<TextView>(
//                    com.google.android.material.R.id.snackbar_text
//                ).apply {
//                    setTextColor(Color.WHITE)
//                }
//            }.show()
//        }
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
