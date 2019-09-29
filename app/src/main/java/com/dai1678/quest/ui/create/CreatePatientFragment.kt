package com.dai1678.quest.ui.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.dai1678.quest.R
import com.dai1678.quest.databinding.FragmentCreatePatientBinding

class CreatePatientFragment : Fragment() {

    private val createPatientViewModel: CreatePatientViewModel by viewModels()

    private lateinit var binding: FragmentCreatePatientBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_create_patient, container, false
        )
        binding.lifecycleOwner = this
        binding.viewModel = createPatientViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.createPatientButton.setOnClickListener {
            createPatientViewModel.onClickRegister()
        }

        createPatientViewModel.response.observe(viewLifecycleOwner, Observer {
            requireActivity().finish()
        })
    }
}
