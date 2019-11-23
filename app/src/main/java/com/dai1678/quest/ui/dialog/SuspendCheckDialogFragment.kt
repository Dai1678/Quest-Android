package com.dai1678.quest.ui.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.dai1678.quest.R

class SuspendCheckDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            AlertDialog.Builder(it, R.style.AlertDialog_Style).apply {
                setMessage(R.string.suspend_check_message)
                setPositiveButton(R.string.suspension_diagnosis) { _, _ ->
                    val action: NavDirections =
                        SuspendCheckDialogFragmentDirections.actionSuspendCheckDialogFragmentToPatientListFragment()
                    findNavController().navigate(action)
                }
                setNegativeButton(R.string.cancel_diagnosis) { dialog, _ ->
                    dialog.cancel()
                }
            }.create()
        } ?: throw IllegalStateException("Activity cannot null")
    }
}
