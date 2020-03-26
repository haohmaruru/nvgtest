package com.example.nvgtest.util

import java.util.*

object DateUtil {
    fun getDateTime(timstamp: Long): String {
        val calendar = Calendar.getInstance()
        calendar.time = Date(timstamp)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        return formatTimeNumber(hour) + ":" + formatTimeNumber(minute) + " " + formatTimeNumber(day) + "/" + formatTimeNumber(month + 1) + "/" + formatTimeNumber(year)
    }
    fun formatTimeNumber(number: Int): String {
        return if (number < 10)
            "0$number"
        else
            number.toString() + ""
    }
}