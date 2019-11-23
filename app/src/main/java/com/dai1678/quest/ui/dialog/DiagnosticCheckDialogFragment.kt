package com.dai1678.quest.ui.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dai1678.quest.R
import com.dai1678.quest.databinding.DialogDiagnosticCheckBinding

class DiagnosticCheckDialogFragment : DialogFragment() {

    private val args: DiagnosticCheckDialogFragmentArgs by navArgs()

    @SuppressLint("SetTextI18n", "InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val patientId = args.patientId
        val lastName = args.patientLastName

        val binding = DataBindingUtil.inflate<DialogDiagnosticCheckBinding>(
            LayoutInflater.from(activity),
            R.layout.dialog_diagnostic_check,
            null,
            false
        )
        binding.titleMessage =
            "$lastName ${resources.getString(R.string.diagnostic_check_dialog_title)}"
        val view = binding.root

        return AlertDialog.Builder(requireContext(), R.style.AlertDialog_Style).apply {
            setView(view)
            setPositiveButton(R.string.start_diagnosis) { _, _ ->
                val action = DiagnosticCheckDialogFragmentDirections
                    .actionDiagnosticCheckDialogFragmentToQuestionnaireStartFragment(patientId)
                findNavController().navigate(action)
            }
            setNegativeButton(R.string.cancel_diagnosis) { dialog, _ ->
                dialog.cancel()
            }
        }.create()
    }
}
