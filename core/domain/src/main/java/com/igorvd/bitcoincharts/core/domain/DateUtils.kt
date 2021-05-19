package com.igorvd.bitcoincharts.core.domain

import java.lang.String.format
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun timeStampToDatePattern(timestamp: Long, pattern: String = "dd/MM/yy"): String {

    val calendar = Calendar.getInstance(Locale.ENGLISH)
    calendar.timeInMillis = timestamp * 1000
    val date = SimpleDateFormat(pattern, Locale.ENGLISH).format(calendar.time)
    return date

}