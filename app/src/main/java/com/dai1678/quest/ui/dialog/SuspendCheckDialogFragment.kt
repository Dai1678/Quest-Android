package com.dai1678.quest.ui.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.dai1678.quest.R

class SuspendCheckDialogFragment : DialogFragment() {

    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val view = requireActivity().layoutInflater.inflate(R.layout.dialog_suspend_check, null)

        return AlertDialog.Builder(requireContext(), R.style.AlertDialog_Style).apply {
            setView(view)
            setPositiveButton(R.string.suspension_diagnosis) { _, _ ->
                requireActivity().finish()
            }
            setNegativeButton(R.string.cancel_diagnosis) { dialog, _ ->
                dialog.cancel()
            }
        }.create()
    }
}
