package com.dai1678.quest.ui.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.dai1678.quest.R

class DiagnosticCheckDialogFragment : DialogFragment() {

    @SuppressLint("SetTextI18n")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val patientName = DiagnosticCheckDialogFragmentArgs.fromBundle(arguments!!).patientFirstName

        val paddingLeftRight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24F, resources.displayMetrics).toInt()
        val paddingTopBottom = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16F, resources.displayMetrics).toInt()


        val titleTextView = TextView(requireContext()).apply {
            setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
            setTextColor(Color.WHITE)
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            setPadding(paddingLeftRight, paddingTopBottom, paddingLeftRight, paddingTopBottom)
            text = "$patientName ${resources.getString(R.string.diagnostic_check_dialog_title)}"
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 18F)
            typeface = Typeface.DEFAULT_BOLD
        }

        return AlertDialog.Builder(requireContext(), R.style.AlertDialog_Style).apply {
            setCustomTitle(titleTextView)
            setMessage(R.string.diagnostic_check_dialog_message)
            setPositiveButton(R.string.start_diagnosis) { _, _ ->
                // TODO アンケート画面へNavigation
                Toast.makeText(context, "Go!", Toast.LENGTH_SHORT).show()
            }
            setNegativeButton(R.string.cancel_diagnosis) { dialog, _ ->
                dialog.cancel()
            }
        }.create()
    }

}
