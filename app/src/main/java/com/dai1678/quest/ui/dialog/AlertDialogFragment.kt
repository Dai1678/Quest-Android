package com.dai1678.quest.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.dai1678.quest.R

/**
 * メッセージとボタンを含むダイアログ
 */
class AlertDialogFragment : DialogFragment() {
    private var alertDialogFragmentListener: AlertDialogFragmentListener? = null
    private val navArgs: AlertDialogFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        alertDialogFragmentListener = when {
            context is AlertDialogFragmentListener -> context
            targetFragment is AlertDialogFragmentListener -> targetFragment as AlertDialogFragmentListener
            else -> null
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val titleResId = navArgs.titleResId
        val titleFormatArgs = navArgs.titleFormatArgs
        val title = if (titleFormatArgs.isNullOrEmpty()) {
            getString(titleResId)
        } else {
            getString(titleResId, *titleFormatArgs)
        }
        val messageResId = navArgs.messageResId
        val messageFormatArgs = navArgs.messageFormatArgs
        val message = if (messageFormatArgs.isNullOrEmpty()) {
            getString(messageResId)
        } else {
            getString(messageResId, *messageFormatArgs)
        }
        val positiveButtonTitle = getString(navArgs.positiveTitleResId)
        val negativeButtonTitle = getString(navArgs.negativeTitleResId)
        val cancelable = navArgs.cancelable
        val activity = activity ?: return super.onCreateDialog(savedInstanceState)

        isCancelable = cancelable
        return AlertDialog.Builder(activity, R.style.DialogStyle).apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton(positiveButtonTitle) { dialog, which ->
                alertDialogFragmentListener?.onPositiveClick(dialog, which)
            }
            if (negativeButtonTitle.isBlank().not()) {
                setNegativeButton(negativeButtonTitle) { dialog, which ->
                    alertDialogFragmentListener?.onNegativeClick(dialog, which)
                }
            }
        }.create()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        (targetFragment as? DialogInterface.OnDismissListener
            ?: activity as? DialogInterface.OnDismissListener)?.onDismiss(dialog)
    }

    /**
     * コールバックを実行する側で複数の処理パターンがある場合は、tagで判別する
     */
    interface AlertDialogFragmentListener {
        fun onPositiveClick(dialog: DialogInterface, which: Int) = Unit
        fun onNegativeClick(dialog: DialogInterface, which: Int) = Unit
    }
}
