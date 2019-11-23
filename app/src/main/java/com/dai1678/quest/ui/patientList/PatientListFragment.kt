package com.dai1678.quest.ui.patientList

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.dai1678.quest.R
import com.dai1678.quest.databinding.FragmentPatientListBinding
import com.google.android.material.snackbar.Snackbar
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.databinding.ViewHolder

class PatientListFragment : Fragment() {

    private val viewModel: PatientListViewModel by viewModels()
    private val groupAdapter = GroupAdapter<ViewHolder<*>>()

    private lateinit var binding: FragmentPatientListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_patient_list, container, false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.isLoading().observe(viewLifecycleOwner) {
            binding.patientListSwipeRefreshLayout.isRefreshing = it
        }

        viewModel.getSnackBarAction().observe(viewLifecycleOwner) {
            Snackbar.make(view, it.text, Snackbar.LENGTH_LONG).apply {
                getView().setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
                getView().findViewById<TextView>(
                    com.google.android.material.R.id.snackbar_text
                ).apply {
                    setTextColor(Color.WHITE)
                }
            }.show()
        }

        binding.patientListRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = groupAdapter
        }

        binding.patientListSwipeRefreshLayout.setOnRefreshListener {
            viewModel.reloadPatientList()
            makePatientList()
        }
    }

    override fun onResume() {
        super.onResume()
        makePatientList()
    }

    private fun makePatientList() {
        val groupList = arrayListOf<Group>()

        viewModel.getPatientList().observe(viewLifecycleOwner) {

            val section = Section()
            it?.let {
                section.setHeader(PatientListHeaderItem())
                for (patient in it.list) section.add(PatientListBodyItem(patient))
            }
            groupList.add(section)
            groupAdapter.update(groupList)
        }
    }
}
