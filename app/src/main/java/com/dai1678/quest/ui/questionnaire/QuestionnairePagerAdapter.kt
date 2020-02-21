package com.dai1678.quest.ui.questionnaire

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dai1678.quest.enums.Question
import com.dai1678.quest.enums.Question.PAGE0
import com.dai1678.quest.enums.Question.PAGE1
import com.dai1678.quest.enums.Question.PAGE10
import com.dai1678.quest.enums.Question.PAGE11
import com.dai1678.quest.enums.Question.PAGE12
import com.dai1678.quest.enums.Question.PAGE13
import com.dai1678.quest.enums.Question.PAGE14
import com.dai1678.quest.enums.Question.PAGE2
import com.dai1678.quest.enums.Question.PAGE3
import com.dai1678.quest.enums.Question.PAGE4
import com.dai1678.quest.enums.Question.PAGE5
import com.dai1678.quest.enums.Question.PAGE6
import com.dai1678.quest.enums.Question.PAGE7
import com.dai1678.quest.enums.Question.PAGE8
import com.dai1678.quest.enums.Question.PAGE9

class QuestionnairePagerAdapter(
    fragment: Fragment,
    private val userId: String
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 15

    override fun createFragment(position: Int): Fragment {
        return when (Question.valueOf(position)) {
            PAGE0 -> QuestionnaireStartFragment()
            PAGE1, PAGE2, PAGE7, PAGE8, PAGE9, PAGE12 ->
                QuestionnaireSimpleAnswerFragment.newInstance()
            PAGE3, PAGE4, PAGE5, PAGE6, PAGE10, PAGE11, PAGE13 ->
                QuestionnaireChildAnswerFragment.newInstance()
            PAGE14 -> QuestionnaireEndFragment.newInstance(userId)
        }
    }
}
