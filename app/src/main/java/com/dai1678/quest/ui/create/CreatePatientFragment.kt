package com.dai1678.quest.ui.create

import android.graphics.Color
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
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_create_patient.*
import kotlinx.android.synthetic.main.activity_create_patient.view.*
import kotlinx.android.synthetic.main.activity_create_patient.view.create_patient_toolbar

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

        requireActivity().create_patient_toolbar.apply {
            setNavigationIcon(R.drawable.ic_close_white_24dp)
            setNavigationOnClickListener {
                requireActivity().onBackPressed()
            }
        }

        binding.createPatientButton.setOnClickListener {
            createPatientViewModel.onClickRegister()
        }

        createPatientViewModel.response.observe(this, Observer {
            val snackBar = Snackbar.make(
                view,
                "作成しました",
                Snackbar.LENGTH_LONG
            )

            snackBar.view.setBackgroundColor(Color.WHITE)

            snackBar.show()

            requireActivity().finish()
        })
    }
}
