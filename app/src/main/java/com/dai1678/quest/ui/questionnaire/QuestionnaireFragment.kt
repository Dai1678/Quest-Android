package com.dai1678.quest.ui.questionnaire

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dai1678.quest.R
import com.dai1678.quest.databinding.FragmentQuestionnaireBinding
import com.dai1678.quest.entity.QuestionChild
import com.dai1678.quest.entity.QuestionSize
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.databinding.ViewHolder

class QuestionnaireFragment : Fragment() {

    private val viewModel: QuestionnaireViewModel by activityViewModels()
    private val groupAdapter = GroupAdapter<ViewHolder<*>>()
    private lateinit var binding: FragmentQuestionnaireBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_questionnaire, container, false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        viewModel.getPage().observe(viewLifecycleOwner) {
            when (it) {
                FIRST_PAGE -> {
                    navController.popBackStack()
                }
                LAST_PAGE -> {
                    navController.navigate(
                        R.id.action_questionnaireFragment_to_questionnaireEndFragment
                    )
                }
                else -> {
                    loadQuestionView()
                }
            }
        }

        binding.questionnaireRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = groupAdapter
        }

        binding.questionnaireBackButton.setOnClickListener {
            viewModel.backPage()
            navController.navigate(R.id.action_global_questionnaire_fragment)
        }

        binding.questionnaireNextButton.setOnClickListener {
            viewModel.nextPage()
            navController.navigate(R.id.action_global_questionnaire_fragment)
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    viewModel.backPage()
                    navController.navigate(R.id.action_global_questionnaire_fragment)
                }
            }
        )
    }

    @SuppressLint("SetTextI18n")
    private fun loadQuestionView() {
        binding.questionnaireNumberText.text = viewModel.getQuestionNumberMessageResId()?.let {
            resources.getString(it)
        }

        binding.questionnaireMainMessageText.text =
            viewModel.getQuestionMainMessageResId()?.let {
                resources.getString(it)
            } ?: ""

        val questionSubNumberMessage = when (viewModel.getPage().value) {
            QuestionSize.PAGE3.pageNumber, QuestionSize.PAGE10.pageNumber ->
                resources.getStringArray(R.array.questionnaire_sub_first_half_numbers)

            QuestionSize.PAGE4.pageNumber, QuestionSize.PAGE11.pageNumber ->
                resources.getStringArray(R.array.questionnaire_sub_latter_half_numbers)

            else -> resources.getStringArray(R.array.questionnaire_sub_numbers)
        }

        val questionSubMessages = viewModel.getQuestionSubMessageResId()?.let {
            resources.getStringArray(it)
        } ?: arrayOf()

        val questionAnswerMessages = viewModel.getQuestionAnswerResId()?.let {
            resources.getStringArray(it)
        } ?: arrayOf()

        val list = arrayListOf<Group>()
        if (questionSubMessages.isNotEmpty()) {
            for (i in questionSubMessages.indices) {
                val questionChild = QuestionChild(
                    questionSubNumberMessage[i],
                    questionSubMessages[i],
                    questionAnswerMessages.toList()
                )
                list.add(QuestionnaireGroup(viewModel, questionChild, true))
            }
        } else {
            val questionChild = QuestionChild(
                null,
                null,
                questionAnswerMessages.toList()
            )
            list.add(QuestionnaireGroup(viewModel, questionChild, false))
        }
        groupAdapter.update(list)
    }

    companion object {
        private const val FIRST_PAGE = 0
        private const val LAST_PAGE = 14
    }
}
