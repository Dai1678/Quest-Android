package com.dai1678.quest.ui.patientList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dai1678.quest.R
import com.dai1678.quest.databinding.FragmentPatientListBinding
import com.dai1678.quest.entity.Patient2
import com.dai1678.quest.ui.questionnaire.QuestionnaireActivity
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.databinding.ViewHolder

class PatientListFragment : Fragment() {

    private val patientListViewModel: PatientListViewModel by viewModels()
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
        binding.viewModel = patientListViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.patientListToolbar.apply {
            inflateMenu(R.menu.patient_list_menu)
            setOnMenuItemClickListener {
                when (it.itemId) {
//                    R.id.reload_patient_list -> patientListViewModel.onRefresh()
                    R.id.intent_create_patient_activity -> {
                        findNavController().navigate(R.id.action_patientListFragment_to_createUserActivity)
                    }
                }
                true
            }
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }

//        patientListViewModel.patientList.observe(viewLifecycleOwner, Observer {
//            groupAdapter.clear()
//            if (it.isNullOrEmpty()) {
//                val section = Section()
//                section.add(LoginDoctorItem("テスト ユーザー"))
//                section.setHeader(PatientListHeaderItem())
//                for (patient in it) section.add(PatientListBodyItem(patient!!))
//                groupAdapter.add(section)
//            }
//        })

        val section = Section()
        section.add(LoginDoctorItem("テスト ユーザー"))
        section.add(PatientListHeaderItem())
        val patient = Patient2("test", "テスト", "ユーザー", "2019-09-23T19:27:00.000Z", listOf())
        for (i in 1..10) section.add(PatientListBodyItem(patient))
        groupAdapter.add(section)

        binding.patientListRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = groupAdapter
        }


    }

    override fun onResume() {
        super.onResume()
//        patientListViewModel.onRefresh()
    }
}
