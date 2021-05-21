package com.igorvd.bitcoincharts.core.domain.formatter

import android.icu.text.CompactDecimalFormat
import java.util.Locale
import javax.inject.Inject

class NumberFormatterImpl @Inject constructor() : NumberFormatter {

    override fun formatCompact(value: Double): String {
        val compactDecimalFormat =
            CompactDecimalFormat.getInstance(
                Locale.ENGLISH,
                CompactDecimalFormat.CompactStyle.SHORT
            ).apply {
                val significantDigits = getSignificantDigits(value)
                maximumSignificantDigits = significantDigits
                minimumSignificantDigits = significantDigits
            }
        return compactDecimalFormat.format(value)
    }

    private fun getSignificantDigits(value: Double): Int {
        var number = value.toLong().toString()
        while (number.last() == '0') {
            number = number.dropLast(1)
        }
        return number.length
    }
}