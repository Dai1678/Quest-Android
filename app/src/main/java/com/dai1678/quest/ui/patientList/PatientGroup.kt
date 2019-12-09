package com.dai1678.quest.ui.patientList

import androidx.navigation.findNavController
import com.dai1678.quest.R
import com.dai1678.quest.databinding.ListHeaderPatientBinding
import com.dai1678.quest.databinding.ListItemPatientBinding
import com.dai1678.quest.entity.Patient
import com.dai1678.quest.util.StringUtils
import com.xwray.groupie.databinding.BindableItem

class PatientListHeaderItem :
    BindableItem<ListHeaderPatientBinding>() {

    override fun getLayout() = R.layout.list_header_patient

    override fun bind(viewBinding: ListHeaderPatientBinding, position: Int) {
        // TODO bottomLayoutを表示して並び替えメニュー起動 -> 反映
    }
}

class PatientListBodyItem(private val patient: Patient) :
    BindableItem<ListItemPatientBinding>(), StringUtils {

    override fun getLayout() = R.layout.list_item_patient

    override fun bind(viewBinding: ListItemPatientBinding, position: Int) {
        viewBinding.patientName = "${patient.firstName} ${patient.lastName}"
        viewBinding.patientNameReading = "${patient.firstNameReading} ${patient.lastNameReading}"

        viewBinding.lastQuestionnaireTime =
            viewBinding.root.resources.getString(R.string.patient_list_last_questionnaire_label) +
                    if (patient.questionnaires.isEmpty()) {
                        viewBinding.root.resources.getString(
                            R.string.patient_list_none_last_questionnaire_label
                        )
                    } else {
                        patient.questionnaires[0].updatedAt.formatDateStr
                    }

        viewBinding.listItemPatientView.setOnClickListener {
            val action =
                PatientListFragmentDirections.actionToDiagnosticCheckDialogFragment(
                    patient.id, patient.lastName
                )
            it.findNavController().navigate(action)
        }
    }
}
