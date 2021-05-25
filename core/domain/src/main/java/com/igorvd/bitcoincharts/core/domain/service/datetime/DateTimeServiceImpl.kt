package com.igorvd.bitcoincharts.core.domain.service.datetime

import com.igorvd.bitcoincharts.core.domain.service.locale.LocaleService
import java.text.SimpleDateFormat
import java.util.Calendar
import javax.inject.Inject

class DateTimeServiceImpl @Inject constructor(
    private val localeService: LocaleService
) : DateTimeService {

    override fun convertTimestampToDatePattern(timestamp: Long, datePattern: DatePattern): String {
        val calendar = Calendar.getInstance(localeService.getLocale())
        calendar.timeInMillis = timestamp * MILLS_FACTOR
        val date =
            SimpleDateFormat(datePattern.pattern, localeService.getLocale()).format(calendar.time)
        return date
    }

    companion object {
        private const val MILLS_FACTOR = 1_000L
    }
}