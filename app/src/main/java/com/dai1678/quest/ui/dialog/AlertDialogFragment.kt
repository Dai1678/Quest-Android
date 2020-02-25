package com.dai1678.quest.ui.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.dai1678.quest.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

private const val DIALOG_TAG = "alertDialogFragment"
private const val DIALOG_REQUEST_CODE = 1234
private const val KEY_TITLE = "title"
private const val KEY_TITLE_FORMAT_ARGS = "titleFormatArgs"
private const val KEY_MESSAGE = "message"
private const val KEY_MESSAGE_FORMAT_ARGS = "messageFormatArgs"
private const val KEY_POSITIVE_BUTTON_TITLE = "positive"
private const val KEY_NEGATIVE_BUTTON_TITLE = "negative"
private const val KEY_CANCELABLE = "cancelable"

@DslMarker
annotation class AlertDialogFragmentBuilderDsl

fun alertDialogFragment(
    setup: AlertDialogFragment.Builder.() -> Unit
): AlertDialogFragment {
    return AlertDialogFragment.Builder().apply(setup).build()
}

/**
 * メッセージとボタンを含むダイアログの汎用クラス
 */
class AlertDialogFragment : DialogFragment() {
    private var alertDialogFragmentListener: AlertDialogFragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // AlertDialogFragmentListenerがimplementされているかチェック
        alertDialogFragmentListener = when {
            context is AlertDialogFragmentListener -> context
            targetFragment is AlertDialogFragmentListener ->
                targetFragment as AlertDialogFragmentListener
            else -> null
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val titleResId = arguments?.getInt(KEY_TITLE) ?: R.string.blank
        val titleFormatArgs = arguments?.getStringArray(KEY_TITLE_FORMAT_ARGS)
        val title = if (titleFormatArgs.isNullOrEmpty()) {
            getString(titleResId)
        } else {
            getString(titleResId, *titleFormatArgs)
        }
        val messageResId = arguments?.getInt(KEY_MESSAGE) ?: R.string.blank
        val messageFormatArgs = arguments?.getStringArray(KEY_MESSAGE_FORMAT_ARGS)
        val message = if (messageFormatArgs.isNullOrEmpty()) {
            getString(messageResId)
        } else {
            getString(messageResId, *messageFormatArgs)
        }
        val positiveButtonTitle =
            getString(arguments?.getInt(KEY_POSITIVE_BUTTON_TITLE) ?: R.string.ok)
        val negativeButtonTitle =
            getString(arguments?.getInt(KEY_NEGATIVE_BUTTON_TITLE) ?: R.string.blank)
        val cancelable = arguments?.getBoolean(KEY_CANCELABLE) ?: true
        val activity = activity ?: return super.onCreateDialog(savedInstanceState)

        isCancelable = cancelable
        return MaterialAlertDialogBuilder(activity).apply {
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

    @Suppress("DEPRECATION")
    fun show(fragmentManager: FragmentManager) {
        show(fragmentManager, DIALOG_TAG)
    }

    @Deprecated(
        "Please use the show method implemented in AlertDialogFragment",
        ReplaceWith("fragment.show(manager, this)")
    )
    override fun show(manager: FragmentManager, tag: String?) {
        super.show(manager, tag)
    }

    @Suppress("DEPRECATION")
    @JvmOverloads
    fun <T> show(
        fragmentManager: FragmentManager,
        fragment: T,
        tag: String? = DIALOG_TAG,
        requestCode: Int = DIALOG_REQUEST_CODE
    ) where T : Fragment, T : AlertDialogFragmentListener {
        setTargetFragment(fragment, requestCode)
        show(fragmentManager, tag)
    }

    /**
     * BuilderをDSLで定義
     * @property titleResId ダイアログのタイトル文字列リソース
     * @property titleFormatArgs ダイアログのタイトルの書式文字列
     * @property messageResId ダイアログのメッセージ文字列リソース
     * @property messageFormatArgs ダイアログのメッセージの書式文字列
     * @property positiveTitleResId ダイアログのPositiveButtonテキスト文字列リソース
     * @property negativeTitleResId ダイアログのNegativeButtonのテキスト文字列リソース
     * @property cancelable ダイアログ外をタップした際にダイアログを閉じるかどうか
     */
    @AlertDialogFragmentBuilderDsl
    class Builder {
        private val args = Bundle()
        var titleResId: Int = R.string.blank
        var titleFormatArgs: Array<String>? = null
        var messageResId: Int = R.string.blank
        var messageFormatArgs: Array<String>? = null
        var positiveTitleResId: Int = R.string.ok
        var negativeTitleResId: Int = R.string.blank
        var cancelable = true

        fun build(): AlertDialogFragment = AlertDialogFragment().apply {
            arguments = args.apply {
                putInt(KEY_TITLE, titleResId)
                putStringArray(KEY_TITLE_FORMAT_ARGS, titleFormatArgs)
                putInt(KEY_MESSAGE, messageResId)
                putStringArray(KEY_MESSAGE_FORMAT_ARGS, messageFormatArgs)
                putInt(KEY_POSITIVE_BUTTON_TITLE, positiveTitleResId)
                putInt(KEY_NEGATIVE_BUTTON_TITLE, negativeTitleResId)
                putBoolean(KEY_CANCELABLE, cancelable)
            }
        }
    }

    /**
     * ダイアログ内のボタンのクリックListener
     * コールバックを実行する側で複数の処理パターンがある場合は、tagで判別する
     */
    interface AlertDialogFragmentListener {
        fun onPositiveClick(dialog: DialogInterface, which: Int) = Unit
        fun onNegativeClick(dialog: DialogInterface, which: Int) = Unit
    }
}
