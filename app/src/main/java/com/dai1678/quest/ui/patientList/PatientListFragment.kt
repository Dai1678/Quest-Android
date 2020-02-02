package com.dai1678.quest.ui.patientList

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
import com.dai1678.quest.databinding.FragmentPatientListBinding
import com.dai1678.quest.listener.PatientListFragmentListener
import com.dai1678.quest.util.setUpRefreshLayout
import com.dai1678.quest.util.setupSnackbar
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.databinding.ViewHolder

class PatientListFragment : Fragment() {

    private val viewModel: PatientListViewModel by viewModels()
    private val groupList = arrayListOf<Group>()
    private val groupAdapter = GroupAdapter<ViewHolder<*>>()

    private lateinit var binding: FragmentPatientListBinding

    private val listener = object : PatientListFragmentListener {
        override fun onClickCreateUserFab(view: View) {
            val action = PatientListFragmentDirections.actionToCreateUserFragment()
            findNavController().navigate(action)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        view?.setupSnackbar(this, viewModel.snackBarText, Toast.LENGTH_LONG)
        this.setUpRefreshLayout(binding.patientListSwipeRefreshLayout)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPatientListBinding.inflate(inflater, container, false).apply {
            viewModel = this@PatientListFragment.viewModel
            listener = this@PatientListFragment.listener
            lifecycleOwner = this@PatientListFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.patientListSwipeRefreshLayout.isRefreshing = it
        }

        viewModel.users.observe(viewLifecycleOwner) { list ->
            groupList.clear()
            val section = Section()
            section.setHeader(PatientListHeaderItem())
            for (patient in list) section.add(PatientListBodyItem(patient))
            groupList.add(section)
            groupAdapter.update(groupList)
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
