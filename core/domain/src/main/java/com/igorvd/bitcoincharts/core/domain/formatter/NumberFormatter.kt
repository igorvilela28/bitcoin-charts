package com.igorvd.bitcoincharts.core.domain.formatter

interface NumberFormatter {
    fun formatCompact(value: Double): String
}