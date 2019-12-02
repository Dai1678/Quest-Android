package com.dai1678.quest.ui.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dai1678.quest.R

class DiagnosticCheckDialogFragment : DialogFragment() {

    private val args: DiagnosticCheckDialogFragmentArgs by navArgs()

    @SuppressLint("SetTextI18n", "InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val patientId = args.patientId
        val lastName = args.patientLastName

        return activity?.let {
            AlertDialog.Builder(it, R.style.DialogStyle).apply {
                setTitle("$lastName ${resources.getString(R.string.diagnostic_check_dialog_title)}")
                setMessage(R.string.diagnostic_check_dialog_message)
                setPositiveButton(R.string.start_diagnosis) { _, _ ->
                    val action =
                        DiagnosticCheckDialogFragmentDirections.actionToQuestionnairePagerFragment()
                    findNavController().navigate(action)
                }
                setNegativeButton(R.string.cancel_diagnosis) { dialog, _ ->
                    dialog.cancel()
                }
            }.create()
        } ?: throw IllegalStateException("Activity cannot null")
    }
}
