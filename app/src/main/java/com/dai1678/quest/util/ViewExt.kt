package com.dai1678.quest.util

import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.observe
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.dai1678.quest.R
import com.google.android.material.snackbar.Snackbar
import java.util.regex.Pattern

/**
 * SnackBar表示のEventの発火をObserveして表示する
 */
fun View.setupSnackBar(
    lifecycleOwner: LifecycleOwner,
    snackBarEvent: LiveData<Event<Int>>,
    timeLength: Int
) {
    snackBarEvent.observe(lifecycleOwner) { event ->
        event.getContentIfNotHandled()?.let {
            Snackbar.make(this, context.getString(it), timeLength).run {
                view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
                view.findViewById<TextView>(
                    com.google.android.material.R.id.snackbar_text
                ).apply {
                    setTextColor(Color.WHITE)
                }
                show()
            }
        }
    }
}

/**
 * SwipeRefreshLayoutの初期化
 */
fun Fragment.setUpRefreshLayout(
    refreshLayout: SwipeRefreshLayout
) {
    refreshLayout.setColorSchemeColors(
        ContextCompat.getColor(requireActivity(), R.color.colorPrimary),
        ContextCompat.getColor(requireActivity(), R.color.colorAccent),
        ContextCompat.getColor(requireActivity(), R.color.colorPrimaryDark)
    )
}

/**
 * TextView内の特定の文字列に対してタップイベントを追加する
 * https://qiita.com/KazaKago/items/ada8dca4bd1b536429a3
 */
fun TextView.addHyperLink(linkText: String, callback: ((view: View) -> Unit)) {
    val spannableMessage = SpannableString(text)
    val pattern = Pattern.compile(linkText)
    val matcher = pattern.matcher(text)
    while (matcher.find()) {
        spannableMessage.setSpan(object : ClickableSpan() {
            override fun onClick(textView: View) {
                callback.invoke(textView)
            }

            override fun updateDrawState(textPaint: TextPaint) {
                super.updateDrawState(textPaint)
                textPaint.color = ContextCompat.getColor(context, R.color.colorPrimary)
                textPaint.isUnderlineText = true
            }
        }, matcher.start(), matcher.end(), Spanned.SPAN_INCLUSIVE_INCLUSIVE)
    }
    text = spannableMessage
    movementMethod = LinkMovementMethod.getInstance()
}

fun TextView.addHyperLink(@StringRes vararg linkTextRes: Int, callback: ((view: View) -> Unit)) {
    linkTextRes.map { addHyperLink(it, callback) }
}

fun TextView.addHyperLink(@StringRes linkTextRes: Int, callback: ((view: View) -> Unit)) {
    addHyperLink(context.getString(linkTextRes), callback)
}

fun TextView.addHyperLink(vararg linkText: String, callback: ((view: View) -> Unit)) {
    linkText.map { addHyperLink(it, callback) }
}
