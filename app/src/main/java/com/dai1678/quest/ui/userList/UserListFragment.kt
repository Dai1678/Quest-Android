package com.dai1678.quest.ui.userList

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
import com.dai1678.quest.databinding.FragmentUserListBinding
import com.dai1678.quest.listener.PatientListFragmentListener
import com.dai1678.quest.util.setUpRefreshLayout
import com.dai1678.quest.util.setupSnackBar
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.databinding.GroupieViewHolder
import java.util.Locale

/**
 * 受検者リスト画面 Fragment
 */
class UserListFragment : Fragment() {

    private val viewModel: UserListViewModel by viewModels()

    private lateinit var binding: FragmentUserListBinding

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
                    UserListBodyItem(it)
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
}
