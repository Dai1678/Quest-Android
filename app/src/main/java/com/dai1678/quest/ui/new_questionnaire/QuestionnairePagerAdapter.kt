package com.dai1678.quest.ui.new_questionnaire

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class QuestionnairePagerAdapter(
    fragment: Fragment, private val patientId: String
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 15

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> QuestionnaireStartFragment()
            1, 2, 7, 8, 9, 12 -> QuestionnaireSimpleAnswerFragment.newInstance(position)
            3, 4, 5, 6, 10, 11, 13 -> QuestionnaireChildAnswerFragment.newInstance(position)
            else -> QuestionnaireEndFragment.newInstance(patientId)
        }
    }

}
