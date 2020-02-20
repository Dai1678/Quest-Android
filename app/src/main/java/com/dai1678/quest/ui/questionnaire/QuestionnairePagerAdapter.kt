package com.dai1678.quest.ui.questionnaire

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dai1678.quest.model.PatientDetail

class QuestionnairePagerAdapter(
    fragment: Fragment,
    private val patientDetail: PatientDetail
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 15

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> QuestionnaireStartFragment()
            1, 2, 7, 8, 9, 12 ->
                QuestionnaireSimpleAnswerFragment.newInstance()
            3, 4, 5, 6, 10, 11, 13 -> QuestionnaireChildAnswerFragment.newInstance(
                position, patientDetail
            )
            else -> QuestionnaireEndFragment.newInstance(
                patientDetail
            )
        }
    }
}
