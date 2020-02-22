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
import com.dai1678.quest.listener.PatientListFragmentListener
import com.dai1678.quest.model.Patient
import com.dai1678.quest.ui.dialog.AlertDialogFragment
import com.dai1678.quest.ui.dialog.alertDialogFragment
import com.dai1678.quest.util.setUpRefreshLayout
import com.dai1678.quest.util.setupSnackBar
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.databinding.GroupieViewHolder
import java.util.Locale

/**
 * 受検者リスト画面 Fragment
 */
class UserListFragment : Fragment(), AlertDialogFragment.AlertDialogFragmentListener {

    private val viewModel: UserListViewModel by viewModels()

    private lateinit var binding: FragmentUserListBinding

    // 受検開始するユーザーデータ
    private lateinit var questionnaireTargetUser: Patient

    private val listener = object : PatientListFragmentListener {
        override fun onClickCreateUserFab(view: View) {
            val action = UserListFragmentDirections.actionToCreateUserFragment()
            findNavController().navigate(action)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        view?.setupSnackBar(this, viewModel.snackBarText, Toast.LENGTH_SHORT)
        this.setUpRefreshLayout(binding.patientListSwipeRefreshLayout)
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
            binding.patientListSwipeRefreshLayout.isRefreshing = it
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
                    it.patient.lastName.toUpperCase(Locale.getDefault()) // TODO 並び替え機能に合わせる
                }
            )
        }

        binding.patientListRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = groupAdapter
        }

        binding.patientListSwipeRefreshLayout.setOnRefreshListener {
            viewModel.getUsers()
        }
    }

    // リストクリック時の処理
    private fun intentToConfirmationDialog(userLastName: String) {
        alertDialogFragment {
            titleResId = R.string.diagnostic_check_dialog_title
            titleFormatArgs = arrayOf(userLastName)
            messageResId = R.string.diagnostic_check_dialog_message
            positiveTitleResId = R.string.start_diagnosis
            negativeTitleResId = R.string.cancel_diagnosis
        }.show(parentFragmentManager, this)
    }

    // 受検開始を押した時の処理
    override fun onPositiveClick(dialog: DialogInterface, which: Int) {
        super.onPositiveClick(dialog, which)
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
