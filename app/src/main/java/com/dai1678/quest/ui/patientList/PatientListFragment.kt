package com.dai1678.quest.ui.patientList

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.recyclerview.widget.LinearLayoutManager
import com.dai1678.quest.R
import com.dai1678.quest.databinding.FragmentPatientListBinding
import com.dai1678.quest.databinding.ListHeaderPatientBinding
import com.dai1678.quest.databinding.ListItemPatientBinding
import com.dai1678.quest.entity.Patient
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.databinding.ViewHolder
import java.text.SimpleDateFormat

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
                val navController = findNavController()
                it.onNavDestinationSelected(navController)
            }
        }

        binding.patientListRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = groupAdapter
        }

        patientListViewModel.patientList.observe(viewLifecycleOwner, Observer {
            it?.let {
                groupAdapter.clear()
                val section = Section()
//                section.setHeader(
//                    HeaderItem(resources.getString(R.string.patient_list_patient_name_ascending_order_label))
//                )
                for (patient in it) section.add(BodyItem(patient))
                groupAdapter.add(section)
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback {
            requireActivity().finish()
        }
    }

    inner class HeaderItem(private val sortCategoryName: String) : BindableItem<ListHeaderPatientBinding>() {
        override fun getLayout() = R.layout.list_header_patient

        override fun bind(viewBinding: ListHeaderPatientBinding, position: Int) {
            viewBinding.sortCategoryName = sortCategoryName
        }
    }

    inner class BodyItem(private val patient: Patient) : BindableItem<ListItemPatientBinding>() {
        private val lastQuestionnaireLabel: String = resources.getString(R.string.patient_list_last_questionnaire_label)

        override fun getLayout() = R.layout.list_item_patient

        override fun bind(viewBinding: ListItemPatientBinding, position: Int) {
            viewBinding.patientName = "${patient.lastName} ${patient.firstName}"
            viewBinding.lastQuestionnaireTime = "$lastQuestionnaireLabel ${getLastQuestionnaireTime(patient)}"
            viewBinding.listItemPatientView.setOnClickListener {
                // TODO 診断開始確認ダイアログ表示
                Toast.makeText(context, "test", Toast.LENGTH_SHORT).show()
            }
        }

        private fun getLastQuestionnaireTime(patient: Patient): String {
            return if (patient.questionnaireId == null) {
                formatLastQuestionnaireTime(patient.updatedAt)
            } else {
                resources.getString(R.string.patient_list_none_last_questionnaire_label)
            }
        }

        @SuppressLint("SimpleDateFormat")
        private fun formatLastQuestionnaireTime(before: String): String {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'")
            val date = dateFormat.parse(before)
            val dateFormat2 = SimpleDateFormat("yyyy年MM月dd日")
            return dateFormat2.format(date)
        }
    }
}
