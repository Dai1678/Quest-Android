package com.dai1678.quest.ui.questionnaire

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.dai1678.quest.R
import com.dai1678.quest.databinding.FragmentQuestionnairePagerBinding
import com.dai1678.quest.enums.Question
import com.dai1678.quest.listener.MainActivityListener
import com.dai1678.quest.listener.QuestionnairePagerFragmentListener
import com.dai1678.quest.ui.dialog.AlertDialogFragment
import com.dai1678.quest.ui.dialog.alertDialogFragment

class QuestionnairePagerFragment : Fragment(),
    QuestionnairePagerViewModel.CallBack,
    AlertDialogFragment.AlertDialogFragmentListener {

    private lateinit var binding: FragmentQuestionnairePagerBinding

    private val args: QuestionnairePagerFragmentArgs by navArgs()
    private val viewModel: QuestionnairePagerViewModel by viewModels()

    private val onPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrollStateChanged(state: Int) = Unit

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) = Unit

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            viewModel.setCurrentPage(position)
            viewModel.sendScreenLog(position, args.userId, args.userGender, args.userAgeRange)
        }
    }

    private val listener = object : QuestionnairePagerFragmentListener {
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
            listener = this@QuestionnairePagerFragment.listener
            viewModel = this@QuestionnairePagerFragment.viewModel.apply {
                callBack = this@QuestionnairePagerFragment
            }
            lifecycleOwner = this@QuestionnairePagerFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        val questionnairePagerAdapter = QuestionnairePagerAdapter(this, args.userId)

        binding.pager.apply {
            adapter = questionnairePagerAdapter
            registerOnPageChangeCallback(onPageChangeCallback)
            isUserInputEnabled = false // 誤スクロール防止のため、横スクロール操作を受け付けない
        }

        viewModel.currentPage.observe(viewLifecycleOwner) {
            viewModel.update()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.questionnaire_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_questionnaire_suspend -> {
                alertDialogFragment {
                    messageResId = R.string.suspend_check_message
                    positiveTitleResId = R.string.suspension_diagnosis
                    negativeTitleResId = R.string.back
                }.show(parentFragmentManager, this)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun updateToolbarTitle(question: Question) {
        val context = context ?: return
        val title = question.getTitle(context)
        (activity as MainActivityListener).updateToolbarTitle(title)
    }

    override fun onPositiveClick(dialog: DialogInterface, which: Int) {
        super.onPositiveClick(dialog, which)
        val action = QuestionnairePagerFragmentDirections.actionGlobalUserListFragment()
        findNavController().navigate(action)
    }
}
