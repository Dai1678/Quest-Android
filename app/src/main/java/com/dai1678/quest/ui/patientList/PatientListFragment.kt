package com.dai1678.quest.ui.patientList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.dai1678.quest.R
import com.dai1678.quest.databinding.FragmentPatientListBinding

class PatientListFragment : Fragment() {

    private val patientListViewModel: PatientListViewModel by viewModels()

    private lateinit var binding: FragmentPatientListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_patient_list, container, false
        )
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
    }
}
