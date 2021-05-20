package com.igorvd.bitcoincharts.core.domain.formatter

interface CurrencyFormatter {
    fun formatUSD(value: Double): String
    fun formatCompact(value: Double): String
}