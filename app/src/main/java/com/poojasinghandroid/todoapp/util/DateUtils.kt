package com.poojasinghandroid.todoapp.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtils {

    private val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

    fun formatDate(timestamp: Long?): String {
        if (timestamp == null) return "No due date"
        return dateFormat.format(Date(timestamp))
    }

    fun todayDate(): String {
        return dateFormat.format(Date())
    }
}