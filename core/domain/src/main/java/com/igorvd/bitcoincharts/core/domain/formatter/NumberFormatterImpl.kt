package com.igorvd.bitcoincharts.core.domain.formatter

import android.icu.text.CompactDecimalFormat
import com.igorvd.bitcoincharts.core.domain.service.locale.LocaleService
import java.text.NumberFormat
import javax.inject.Inject
import kotlin.math.roundToLong

class NumberFormatterImpl @Inject constructor(
    private val localeService: LocaleService
) : NumberFormatter {

    override fun format(value: Long): String {
        return NumberFormat.getInstance(localeService.getLocale()).format(value)
    }

    override fun formatCompact(value: Double): String {
        val compactDecimalFormat =
            CompactDecimalFormat.getInstance(
                localeService.getLocale(),
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