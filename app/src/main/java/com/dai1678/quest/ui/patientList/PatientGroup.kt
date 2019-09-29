package com.dai1678.quest.ui.patientList

import androidx.navigation.findNavController
import com.dai1678.quest.App
import com.dai1678.quest.R
import com.dai1678.quest.databinding.ListHeaderPatientBinding
import com.dai1678.quest.databinding.ListItemLoginDoctorBinding
import com.dai1678.quest.databinding.ListItemPatientBinding
import com.dai1678.quest.entity.Patient2
import com.dai1678.quest.util.StringUtils
import com.xwray.groupie.databinding.BindableItem

class LoginDoctorItem(private val doctorName: String) : BindableItem<ListItemLoginDoctorBinding>() {

    override fun getLayout(): Int = R.layout.list_item_login_doctor

    override fun bind(viewBinding: ListItemLoginDoctorBinding, position: Int) {
        viewBinding.loginDoctorName = doctorName

        viewBinding.listItemDoctorLogoutButton.setOnClickListener {
            it.findNavController().popBackStack()
        }
    }

}

class PatientListHeaderItem :
    BindableItem<ListHeaderPatientBinding>() {

    private val application = App.instance

    override fun getLayout() = R.layout.list_header_patient

    override fun bind(viewBinding: ListHeaderPatientBinding, position: Int) {
        // TODO bottomLayoutを表示して並び替えメニュー起動 -> 反映
    }
}

class PatientListBodyItem(private val patient: Patient2) :
    BindableItem<ListItemPatientBinding>(), StringUtils {

    private val application = App.instance

    private val lastQuestionnaireLabel: String =
        application.resources.getString(R.string.patient_list_last_questionnaire_label)

    override fun getLayout() = R.layout.list_item_patient

    override fun bind(viewBinding: ListItemPatientBinding, position: Int) {
        viewBinding.patientName = "${patient.lastName} ${patient.firstName}"
        viewBinding.lastQuestionnaireTime =
            "$lastQuestionnaireLabel ${getLastQuestionnaireTime(patient)}"

        viewBinding.listItemPatientView.setOnClickListener {
            val action =
                PatientListFragmentDirections
                    .actionPatientListFragmentToDiagnosticCheckDialogFragment(
                        patient.id, patient.lastName
                    )
            it.findNavController().navigate(action)
        }
    }

    private fun getLastQuestionnaireTime(patient: Patient2): String {
        return if (patient.questionnaires.isEmpty()) {
            application.resources.getString(R.string.patient_list_none_last_questionnaire_label)
        } else {
            patient.questionnaires[0].updatedAt.formatDateStr
        }
    }
}
