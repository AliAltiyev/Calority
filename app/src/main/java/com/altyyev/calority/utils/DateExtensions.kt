package com.altyyev.calority.utils

import java.text.SimpleDateFormat
import java.util.*

fun Date.toFormat(dateFormat: String): String {
    return SimpleDateFormat(dateFormat, Locale.getDefault()).format(this)
}




fun Date.startOfDay(): Date {
    val calendar = Calendar.getInstance()
    calendar.time = this
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.HOUR, 0)
    return calendar.time
}

fun Date.endOfDay(): Date {
    val calendar = Calendar.getInstance()
    calendar.time = this
    calendar.set(Calendar.SECOND, 59)
    calendar.set(Calendar.MINUTE, 59)
    calendar.set(Calendar.HOUR, 23)
    return calendar.time
}


fun Date.nextDay(): Date {
    val calendar = Calendar.getInstance()
    calendar.time = this
    calendar.add(Calendar.DATE, +1)
    return calendar.time
}

fun Date.previousDay(): Date {
    val calendar = Calendar.getInstance()
    calendar.time = this
    calendar.add(Calendar.DATE, -1)
    return calendar.time
}

