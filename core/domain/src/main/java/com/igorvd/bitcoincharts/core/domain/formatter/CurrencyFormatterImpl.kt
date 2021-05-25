package com.igorvd.bitcoincharts.core.domain.formatter

import com.igorvd.bitcoincharts.core.domain.service.locale.LocaleService
import java.text.NumberFormat
import java.util.Currency
import javax.inject.Inject

class CurrencyFormatterImpl @Inject constructor(
    private val localeService: LocaleService
) : CurrencyFormatter {

    override fun formatUSD(value: Double): String {
        val format = NumberFormat.getCurrencyInstance(localeService.getLocale()).apply {
            currency = Currency.getInstance(USD_CURRENCY_CODE)
        }
        return format.format(value)
    }

    companion object {
        const val USD_CURRENCY_CODE = "USD"
    }
}