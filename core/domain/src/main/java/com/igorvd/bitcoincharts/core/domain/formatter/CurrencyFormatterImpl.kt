package com.igorvd.bitcoincharts.core.domain.formatter

import java.text.NumberFormat
import java.util.Currency
import java.util.Locale
import javax.inject.Inject

class CurrencyFormatterImpl @Inject constructor() : CurrencyFormatter {

    override fun formatUSD(value: Double): String {
        val format = NumberFormat.getCurrencyInstance(Locale.getDefault())
        format.setCurrency(Currency.getInstance("USD"))
        val result = format.format(value)
        return result
    }
}