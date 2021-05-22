package com.igorvd.bitcoincharts.core.domain.service.datetime

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

class DateTimeServiceImpl @Inject constructor() : DateTimeService {

    override fun convertTimestampToDatePattern(timestamp: Long, datePattern: DatePattern): String {
        val calendar = Calendar.getInstance(Locale.ENGLISH)
        calendar.timeInMillis = timestamp * MILLS_FACTOR
        val date = SimpleDateFormat(datePattern.pattern, Locale.ENGLISH).format(calendar.time)
        return date
    }

    companion object {
        private const val MILLS_FACTOR = 1_000L
    }
}