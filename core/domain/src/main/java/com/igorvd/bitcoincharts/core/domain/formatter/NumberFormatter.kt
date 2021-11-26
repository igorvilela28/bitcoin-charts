package com.igorvd.bitcoincharts.core.domain.formatter

interface NumberFormatter {
    fun format(value: Long): String
    fun formatCompact(value: Double): String
}