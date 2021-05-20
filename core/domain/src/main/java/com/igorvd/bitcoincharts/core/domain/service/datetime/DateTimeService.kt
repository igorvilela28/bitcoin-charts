package com.igorvd.bitcoincharts.core.domain.service.datetime

interface DateTimeService {

    fun convertTimestampToDatePattern(timestamp: Long, datePattern: DatePattern): String

}