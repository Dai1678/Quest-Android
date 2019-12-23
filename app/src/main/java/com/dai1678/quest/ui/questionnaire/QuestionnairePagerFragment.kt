package com.dai1678.quest.ui.questionnaire

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.dai1678.quest.R
import com.dai1678.quest.databinding.FragmentQuestionnairePagerBinding
import com.dai1678.quest.listener.QuestionnairePagerFragmentListener

class QuestionnairePagerFragment : Fragment() {

    private lateinit var binding: FragmentQuestionnairePagerBinding

    private val args: QuestionnairePagerFragmentArgs by navArgs()
    private val questionnairePagerViewModel: QuestionnairePagerViewModel by viewModels()

    private val onPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrollStateChanged(state: Int) = Unit

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) = Unit

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
                    questionnairePagerViewModel.sendScreenLog(position, args.patientDetail)
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

        val questionnairePagerAdapter = QuestionnairePagerAdapter(this, args.patientDetail)

        binding.pager.apply {
            adapter = questionnairePagerAdapter
            registerOnPageChangeCallback(onPageChangeCallback)
            isUserInputEnabled = false // 誤スクロール防止のため、横スクロール操作を受け付けない
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.questionnaire_menu, menu)
    }
}
