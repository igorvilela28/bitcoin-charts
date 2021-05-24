package com.igorvd.bitcoincharts.core.domain.formatter

import com.igorvd.bitcoincharts.core.domain.service.locale.LocaleService
import java.text.NumberFormat
import java.util.Locale
import javax.inject.Inject

class BitcoinFormatterImpl @Inject constructor(
    private val localeService: LocaleService
) : BitcoinFormatter {

    override fun format(
        value: Double,
        fractionDigits: Int,
        appendUnit: Boolean
    ): String {
        val numberFormat = NumberFormat.getInstance(localeService.getLocale()).apply {
            minimumFractionDigits = fractionDigits
            maximumFractionDigits = fractionDigits
        }
        val formattedValue = numberFormat.format(value)
        return if (appendUnit) "$formattedValue ${BitcoinFormatter.BITCOIN_UNIT}" else formattedValue
    }
}