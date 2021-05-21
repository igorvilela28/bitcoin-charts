package com.igorvd.bitcoincharts.core.domain.formatter

interface NumberFormatter {
    fun format(value: Int): String
    fun formatCompact(value: Double): String
}