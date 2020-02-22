package com.dai1678.quest.enums

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

enum class DateFormat(val format: String) {
    M_JP("M月"),
    MD_JP("M月d日"),
    MD_HHMM_JP("M月d日 HH:mm"),
    MD_HHMMSS_JP("M月d日 HH:mm:ss"),
    YYYYM_JP("yyyy年M月"),
    YYYYMMDD_DAYOFWEEK_JP("yyyy年M月d日（E）"),
    MD_DAYOFWEEK_JP("M月d日（E）"),
    YYYYMD_JP("yyyy年M月d日"),
    YYYYMMDD("yyyy/MM/dd"),
    YYYYMM("yyyy/MM"),
    MMDD("MM/dd"),
    MMDDHHMM("MM/dd HH:mm"),
    YYYYMMDD_HYPHEN("yyyy-MM-dd"),
    YYYYMMDD_HHMMSS("yyyy-MM-dd HH:mm:ss"),
    PROFILE_DATE_FORMAT("yyyy-MM-dd_HH"),
    YYYYMMDD_TIMEZONE_HHMMSS("yyyy-MM-dd'T'HH:mm:ss.sss'Z'");

    @JvmOverloads
    fun format(date: Date, locale: Locale = Locale.JAPAN): String {
        return SimpleDateFormat(format, locale).format(date)
    }

    @JvmOverloads
    fun format(millis: Long, locale: Locale = Locale.JAPAN): String {
        return SimpleDateFormat(format, locale).format(millis)
    }

    @JvmOverloads
    fun parse(date: String, locale: Locale = Locale.JAPAN): Date? {
        return try {
            SimpleDateFormat(format, locale).parse(date)
        } catch (e: ParseException) {
            null
        }
    }
}
