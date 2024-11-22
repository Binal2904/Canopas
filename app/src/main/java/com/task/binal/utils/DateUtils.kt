package com.task.binal.utils

import java.text.SimpleDateFormat
import java.util.Calendar

class DateUtils {
    companion object {
        fun getDate(milliSeconds: Long, dateFormat: String?): String {
            val formatter = SimpleDateFormat(dateFormat)
            val calendar: Calendar = Calendar.getInstance()
            calendar.setTimeInMillis(milliSeconds)
            return formatter.format(calendar.time)
        }

        fun isBefore(milliSeconds: Long): Boolean {
            val currentTime = Calendar.getInstance()
            val timeToMatch = Calendar.getInstance()
            timeToMatch.timeInMillis = milliSeconds

            return currentTime < timeToMatch
        }
    }
}