package com.dai1678.quest.util

import android.view.View
import androidx.databinding.BindingAdapter

/**
 * DataBindingの独自Adapterの定義クラス
 */
class DataBindingHelper {
    companion object {
        /**
         * trueの場合ViewをVISIBLE、falseの場合GONEに設定する
         * @param view Visibleを操作する対象のView
         * @param value Visibleのフラグ
         */
        @JvmStatic
        @BindingAdapter("visibilityGone")
        fun setVisibilityGone(view: View?, value: Boolean?) {
            view?.visibility = if (value == true) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }

        /**
         * trueの場合ViewをVISIBLE、falseの場合INVISIBLEに設定する
         * @param view Visibleを操作する対象のView
         * @param value Visibleのフラグ
         */
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
