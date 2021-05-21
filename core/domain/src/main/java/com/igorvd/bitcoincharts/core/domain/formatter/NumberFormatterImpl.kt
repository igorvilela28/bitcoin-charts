package com.igorvd.bitcoincharts.core.domain.formatter

import android.icu.text.CompactDecimalFormat
import java.util.Locale
import javax.inject.Inject
import kotlin.math.roundToLong

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
        var numberString = value.roundToLong().toString()
        while (numberString.last() == '0' && numberString.length > 1) {
            numberString = numberString.dropLast(1)
        }
        return if (numberString.length >= ONE_MILLION.toString().length - 1 && value >= ONE_MILLION) {
            ONE_MILLION_DIGITS
        } else {
            numberString.length
        }
    }

    companion object {
        const val ONE_MILLION = 1_000_000L
        const val ONE_MILLION_DIGITS = 4
    }
}