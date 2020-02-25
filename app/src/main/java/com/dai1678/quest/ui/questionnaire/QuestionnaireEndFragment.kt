package com.dai1678.quest.ui.questionnaire

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dai1678.quest.R
import com.dai1678.quest.databinding.FragmentQuestionnaireEndBinding
import com.dai1678.quest.listener.QuestionnaireEndFragmentListener
import com.dai1678.quest.util.setupSnackBar

/**
 * 回答送信画面のUIコントローラ
 */
class QuestionnaireEndFragment : Fragment() {

    private val questionnaireAnswerViewModel: QuestionnaireAnswerViewModel by viewModels({
        requireParentFragment()
    })

    private lateinit var binding: FragmentQuestionnaireEndBinding

    private val questionnaireEndFragmentListener = object : QuestionnaireEndFragmentListener {
        override fun onClickSendAnswer(view: View) {
            val userId = arguments?.getString(KEY_USER_ID) ?: "empty"
            questionnaireAnswerViewModel.sendQuestionnaireResult(userId)
        }
    }

    private val callbackListener = object : QuestionnaireAnswerViewModel.Callback {
        override fun finishQuestionnaire() {
            // 受検者一覧画面に遷移
            findNavController().navigate(R.id.action_global_user_list_fragment)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        view?.setupSnackBar(this, questionnaireAnswerViewModel.snackBarText, Toast.LENGTH_SHORT)
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

    companion object {

        private const val KEY_USER_ID = "userId"

        fun newInstance(userId: String): Fragment {
            return QuestionnaireEndFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_USER_ID, userId)
                }
            }
        }
    }
}
