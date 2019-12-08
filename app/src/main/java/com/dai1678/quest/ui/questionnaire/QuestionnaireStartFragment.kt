package com.dai1678.quest.ui.questionnaire

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dai1678.quest.databinding.FragmentQuestionnaireStartBinding

class QuestionnaireStartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentQuestionnaireStartBinding.inflate(inflater, container, false).root
    }
}
