package com.dai1678.quest.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

interface StringUtils {
    val String.formatDateStr: String
        @SuppressLint("SimpleDateFormat")
        get() = run {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'")
            val date = dateFormat.parse(this) ?: Date()
            val dateFormat2 = SimpleDateFormat("yyyy年MM月dd日")
            dateFormat2.format(date)
        }
}
