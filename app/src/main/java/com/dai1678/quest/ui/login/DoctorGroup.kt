package com.dai1678.quest.ui.login

import androidx.navigation.findNavController
import com.dai1678.quest.R
import com.dai1678.quest.databinding.ListItemDoctorBinding
import com.dai1678.quest.entity.Doctor
import com.dai1678.quest.util.PreferenceService
import com.dai1678.quest.util.StringUtils
import com.xwray.groupie.databinding.BindableItem

class DoctorGroup(private val doctor: Doctor) :
    BindableItem<ListItemDoctorBinding>(), StringUtils {

    override fun getLayout(): Int = R.layout.list_item_doctor

    override fun bind(viewBinding: ListItemDoctorBinding, position: Int) {

        viewBinding.doctorName = "${doctor.lastName} ${doctor.firstName}"
        viewBinding.doctorNameReading = "${doctor.lastNameReading} ${doctor.firstNameReading}"

        viewBinding.listItemDoctorView.setOnClickListener {
            PreferenceService.deleteLoggedInDoctorId()
            PreferenceService.registerLoggedInDoctorId(doctor.id)

            val action =
                LoginFragmentDirections.actionLoginFragmentToPatientListFragment().apply {
                    loginUserName = "${doctor.lastName} ${doctor.firstName}"
                    loginUserNameReading = "${doctor.lastNameReading} ${doctor.firstNameReading}"
                }
            it.findNavController().navigate(action)
        }
    }
}
