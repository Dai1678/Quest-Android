package com.dai1678.quest.util

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.observe
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.dai1678.quest.R
import com.google.android.material.snackbar.Snackbar

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

fun Fragment.setUpRefreshLayout(
    refreshLayout: SwipeRefreshLayout
) {
    refreshLayout.setColorSchemeColors(
        ContextCompat.getColor(requireActivity(), R.color.colorPrimary),
        ContextCompat.getColor(requireActivity(), R.color.colorAccent),
        ContextCompat.getColor(requireActivity(), R.color.colorPrimaryDark)
    )
}
