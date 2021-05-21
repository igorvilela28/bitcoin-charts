package com.igorvd.bitcoincharts.core.domain.formatter

interface BitcoinFormatter {
    fun format(value: Float, fractionDigits: Int, appendUnit: Boolean = false): String

    companion object  {
        const val BITCOIN_UNIT = "BTC"
    }
}