package com.dai1678.quest.ui.userList

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dai1678.quest.R
import com.dai1678.quest.databinding.FragmentUserListBinding
import com.dai1678.quest.listener.UserListFragmentListener
import com.dai1678.quest.model.User
import com.dai1678.quest.ui.dialog.AlertDialogFragment
import com.dai1678.quest.ui.dialog.alertDialogFragment
import com.dai1678.quest.util.setUpRefreshLayout
import com.dai1678.quest.util.setupSnackBar
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.databinding.GroupieViewHolder
import java.util.Locale

/**
 * 受検者一覧画面のUIコントローラ
 * @property questionnaireTargetUser 受検開始するユーザーデータ
 */
class UserListFragment : Fragment(), AlertDialogFragment.AlertDialogFragmentListener {

    private val viewModel: UserListViewModel by viewModels()

    private lateinit var binding: FragmentUserListBinding

    private lateinit var questionnaireTargetUser: User

    private val listener = object : UserListFragmentListener {
        override fun onClickCreateUserFab(view: View) {
            // 受検者登録画面へ遷移
            val action = UserListFragmentDirections.actionToCreateUserFragment()
            findNavController().navigate(action)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        view?.setupSnackBar(this, viewModel.snackBarText, Toast.LENGTH_SHORT)
        this.setUpRefreshLayout(binding.userListSwipeRefreshLayout)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserListBinding.inflate(inflater, container, false).apply {
            viewModel = this@UserListFragment.viewModel
            listener = this@UserListFragment.listener
            lifecycleOwner = this@UserListFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val groupAdapter = GroupAdapter<GroupieViewHolder<*>>()

        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.userListSwipeRefreshLayout.isRefreshing = it
        }

        viewModel.users.observe(viewLifecycleOwner) { userList ->
            groupAdapter.clear()
//            groupAdapter.add(UserListHeaderItem("名前(昇順)")) // TODO 並び替え機能の実装後に有効化
            groupAdapter.addAll(
                userList.map {
                    UserListBodyItem(it) {
                        questionnaireTargetUser = it
                        intentToConfirmationDialog(it.lastName)
                    }
                }.sortedBy {
                    it.user.lastName.toUpperCase(Locale.getDefault()) // TODO 並び替え機能に合わせる
                }
            )
        }

        binding.userListRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = groupAdapter
        }

        binding.userListSwipeRefreshLayout.setOnRefreshListener {
            viewModel.getUsers()
        }
    }

    /**
     * 受検者リストクリック時の処理
     * @param userLastName 受検者の姓
     */
    private fun intentToConfirmationDialog(userLastName: String) {
        // 受検確認ダイアログの表示
        alertDialogFragment {
            titleResId = R.string.diagnostic_check_dialog_title
            titleFormatArgs = arrayOf(userLastName)
            messageResId = R.string.diagnostic_check_dialog_message
            positiveTitleResId = R.string.start_diagnosis
            negativeTitleResId = R.string.back
        }.show(parentFragmentManager, this)
    }

    /**
     * 受検開始を押した時の処理
     */
    override fun onPositiveClick(dialog: DialogInterface, which: Int) {
        super.onPositiveClick(dialog, which)
        // 回答画面へ遷移
        questionnaireTargetUser.let { user ->
            val action = UserListFragmentDirections.actionToQuestionnairePagerFragment().apply {
                userId = user.id
                userGender = user.gender
                userAgeRange = user.ageRange
            }
            findNavController().navigate(action)
        }
    }
}
