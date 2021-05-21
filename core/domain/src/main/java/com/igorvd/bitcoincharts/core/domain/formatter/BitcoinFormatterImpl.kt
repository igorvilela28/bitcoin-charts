package com.igorvd.bitcoincharts.core.domain.formatter

import java.text.NumberFormat
import java.util.Locale
import javax.inject.Inject

class BitcoinFormatterImpl @Inject constructor() : BitcoinFormatter {

    override fun format(
        value: Double,
        fractionDigits: Int,
        appendUnit: Boolean
    ): String {
        val numberFormat = NumberFormat.getInstance(Locale.getDefault()).apply {
            minimumFractionDigits = fractionDigits
            maximumFractionDigits = fractionDigits
        }
        val formattedValue = numberFormat.format(value)
        return if (appendUnit) "$formattedValue ${BitcoinFormatter.BITCOIN_UNIT}" else formattedValue
    }
}