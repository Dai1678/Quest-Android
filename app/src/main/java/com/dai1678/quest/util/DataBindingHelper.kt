package com.dai1678.quest.util

import android.view.View
import androidx.databinding.BindingAdapter

class DataBindingHelper {
    companion object {
        @JvmStatic
        @BindingAdapter("visibilityGone")
        fun setVisibilityGone(view: View?, value: Boolean?) {
            view?.visibility = if (value == true) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }

        @JvmStatic
        @BindingAdapter("visibilityInvisible")
        fun setVisibilityInvisible(view: View?, value: Boolean?) {
            view?.visibility = if (value == true) {
                View.VISIBLE
            } else {
                View.INVISIBLE
            }
        }
    }
}
