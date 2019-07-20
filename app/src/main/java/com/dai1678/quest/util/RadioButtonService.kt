package com.dai1678.quest.util

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.widget.RadioButton
import androidx.core.content.ContextCompat
import com.dai1678.quest.R

object RadioButtonService {
    fun getRadioButtonSetting(context: Context, message: String): RadioButton {
        val colorStateList = ColorStateList(
            arrayOf(
                intArrayOf(-android.R.attr.state_enabled), // disabled
                intArrayOf(android.R.attr.state_enabled) // enabled
            ),
            intArrayOf(
                Color.BLACK, // disabled
                ContextCompat.getColor(context, R.color.colorPrimary) // enabled
            )
        )

        return RadioButton(context).apply {
            text = message
//            textSize = resources.getDimension(R.dimen.text_size_micro)
            buttonTintList = colorStateList
            invalidate()
        }
    }
}
