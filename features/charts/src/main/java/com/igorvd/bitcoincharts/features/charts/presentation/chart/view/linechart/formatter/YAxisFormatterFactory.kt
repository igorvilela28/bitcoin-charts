package com.igorvd.bitcoincharts.features.charts.presentation.chart.view.linechart.formatter

import com.igorvd.bitcoincharts.core.domain.formatter.BitcoinFormatter
import com.igorvd.bitcoincharts.core.domain.formatter.NumberFormatter
import com.igorvd.bitcoincharts.features.charts.domain.model.ChartType
import javax.inject.Inject

class YAxisFormatterFactory @Inject constructor(
    private val bitcoinFormatter: BitcoinFormatter,
    private val numberFormatter: NumberFormatter
) {

    fun format(chartType: ChartType, value: Float): String {
        return when (chartType) {
            ChartType.NUMBER_OF_TRANSACTIONS,
            ChartType.MARKET_PRICE -> numberFormatter.formatCompact(value.toDouble())
            ChartType.TOTAL_TRANSACTION_FEES -> bitcoinFormatter.format(value, 0)
        }
    }
}