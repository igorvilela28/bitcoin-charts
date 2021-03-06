package com.igorvd.bitcoincharts.features.charts.data.network.mapper

import com.igorvd.bitcoincharts.core.domain.formatter.BitcoinFormatter
import com.igorvd.bitcoincharts.core.domain.formatter.CurrencyFormatter
import com.igorvd.bitcoincharts.core.domain.formatter.NumberFormatter
import com.igorvd.bitcoincharts.features.charts.domain.model.ChartType
import javax.inject.Inject

class MetricScreenChartYMapper @Inject constructor(
    private val currencyFormatter: CurrencyFormatter,
    private val bitcoinFormatter: BitcoinFormatter,
    private val numberFormatter: NumberFormatter
) {

    fun formatYValue(y: Double, chartType: ChartType): String {

        val formattedY = when (chartType) {
            ChartType.MARKET_PRICE -> currencyFormatter.formatUSD(y)
            ChartType.TOTAL_TRANSACTION_FEES -> bitcoinFormatter.format(
                y,
                TOTAL_TRANSACTION_FEES_FRACTION_DIGITS,
                true
            )
            ChartType.NUMBER_OF_TRANSACTIONS,
            ChartType.MINERS_REVENUE -> {
                numberFormatter.formatCompact(y)
            }
            ChartType.OUTPUT_VOLUME,
            ChartType.ESTIMATED_TRANSACTION_VOLUME_BTC -> {
                "${numberFormatter.formatCompact(y)} ${BitcoinFormatter.BITCOIN_UNIT}"
            }
        }
        return formattedY
    }

    companion object {
        const val TOTAL_TRANSACTION_FEES_FRACTION_DIGITS = 3
    }
}