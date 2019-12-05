package com.dai1678.quest.ui.new_questionnaire

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.dai1678.quest.R
import com.dai1678.quest.databinding.FragmentQuestionnairePagerBinding
import com.dai1678.quest.listener.QuestionnairePagerFragmentListener

class QuestionnairePagerFragment : Fragment() {

    private lateinit var binding: FragmentQuestionnairePagerBinding

    private val onPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrollStateChanged(state: Int) {
            super.onPageScrollStateChanged(state)
        }

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels)
        }

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            when (position) {
                0 -> {
                    binding.questionnaireBackButton.visibility = View.INVISIBLE
                }
                14 -> {
                    binding.questionnaireNextButton.visibility = View.INVISIBLE
                }
                else -> {
                    binding.questionnaireBackButton.visibility = View.VISIBLE
                    binding.questionnaireNextButton.visibility = View.VISIBLE
                }
            }
        }
    }

    private val questionnairePagerFragmentListener = object : QuestionnairePagerFragmentListener {
        override fun onClickBack(view: View) {
            binding.pager.setCurrentItem(binding.pager.currentItem - 1, true)
        }

        override fun onClickNext(view: View) {
            binding.pager.setCurrentItem(binding.pager.currentItem + 1, true)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuestionnairePagerBinding.inflate(inflater, container, false).apply {
            listener = questionnairePagerFragmentListener
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        val questionnairePagerAdapter = QuestionnairePagerAdapter(this)

        binding.pager.apply {
            adapter = questionnairePagerAdapter
            registerOnPageChangeCallback(onPageChangeCallback)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.questionnaire_menu, menu)
    }
}