package com.igorvd.bitcoincharts.core.domain.formatter

import java.text.NumberFormat
import java.util.Currency
import java.util.Locale
import javax.inject.Inject

class CurrencyFormatterImpl @Inject constructor() : CurrencyFormatter {

    override fun formatUSD(value: Double): String {
        val format = NumberFormat.getCurrencyInstance(Locale.getDefault()).apply {
            currency = Currency.getInstance(USD_CURRENCY_CODE)
        }
        return format.format(value)
    }

    companion object {
        const val USD_CURRENCY_CODE = "USD"
    }
}