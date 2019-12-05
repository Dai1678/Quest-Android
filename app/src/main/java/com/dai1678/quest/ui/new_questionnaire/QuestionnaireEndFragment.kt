package com.dai1678.quest.ui.new_questionnaire

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProviders
import com.dai1678.quest.databinding.FragmentQuestionnaireEndBinding
import com.dai1678.quest.listener.QuestionnaireEndFragmentListener

class QuestionnaireEndFragment : Fragment() {

    private val questionnaireAnswerViewModel: QuestionnaireAnswerViewModel by viewModels({ requireParentFragment() })

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
}
