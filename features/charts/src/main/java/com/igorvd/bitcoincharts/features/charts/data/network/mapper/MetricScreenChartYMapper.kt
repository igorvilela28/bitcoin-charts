package com.igorvd.bitcoincharts.features.charts.data.network.mapper

import com.igorvd.bitcoincharts.core.domain.formatter.BitcoinFormatter
import com.igorvd.bitcoincharts.core.domain.formatter.CurrencyFormatter
import com.igorvd.bitcoincharts.features.charts.data.network.model.ChartEntryResponse
import com.igorvd.bitcoincharts.features.charts.domain.model.ChartType
import javax.inject.Inject

class MetricScreenChartYMapper @Inject constructor(
    private val currencyFormatter: CurrencyFormatter,
    private val bitcoinFormatter: BitcoinFormatter
) {

    fun mapYMinValue(entries: List<ChartEntryResponse>, chartType: ChartType): Float {

        val minValue = when (chartType) {
            ChartType.MARKET_PRICE -> 0F
            ChartType.TOTAL_TRANSACTION_FEES -> 0F
        }
        return minValue
    }

    fun formatYValue(y: Double, chartType: ChartType): String {

        val formattedY = when (chartType) {
            ChartType.MARKET_PRICE -> currencyFormatter.formatUSD(y)
            ChartType.TOTAL_TRANSACTION_FEES -> bitcoinFormatter.format(
                y.toFloat(),
                TOTAL_TRANSACTION_FEES_FRACTION_DIGITS,
                true
            )
        }
        return formattedY
    }

    companion object {
        private const val TOTAL_TRANSACTION_FEES_FRACTION_DIGITS = 3
    }
}