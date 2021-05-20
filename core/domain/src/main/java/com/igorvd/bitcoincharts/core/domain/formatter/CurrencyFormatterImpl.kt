package com.igorvd.bitcoincharts.core.domain.formatter

import android.icu.text.CompactDecimalFormat
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale
import javax.inject.Inject

class CurrencyFormatterImpl @Inject constructor() : CurrencyFormatter {

    override fun formatUSD(value: Double): String {
        val format = NumberFormat.getCurrencyInstance(Locale.getDefault()).apply {
            currency = Currency.getInstance("USD")
        }
        return format.format(value)
    }

    override fun formatCompact(value: Double): String {
        val compactDecimalFormat =
            CompactDecimalFormat.getInstance(
                Locale.getDefault(),
                CompactDecimalFormat.CompactStyle.SHORT
            )
        return compactDecimalFormat.format(value)
    }
}