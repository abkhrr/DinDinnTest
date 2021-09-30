package com.food_dev.utils.ext.date

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    @JvmStatic
    fun toStringDate(date: Date) : String {
        val format = SimpleDateFormat("dd/MM/yyy", Locale.getDefault())
        return format.format(date)
    }

    @JvmStatic
    fun toLongDate(date: String) : Long {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        return format.parse(date).time
    }

    fun convertLongToTime(time: Long): String {
        val date   = Date(time)
        val format = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.getDefault())
        return format.format(date)
    }

    fun currentTimeToLong(): Long {
        return System.currentTimeMillis()
    }

    fun convertDateToLong(date: String): Long {
        val df = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.getDefault())
        return df.parse(date).time
    }
}