package com.igorvd.bitcoincharts.features.charts.presentation.chart.view.linechart.formatter

import com.igorvd.bitcoincharts.core.domain.formatter.BitcoinFormatter
import com.igorvd.bitcoincharts.core.domain.formatter.CurrencyFormatter
import com.igorvd.bitcoincharts.features.charts.domain.model.ChartType
import javax.inject.Inject

class YAxisFormatterFactory @Inject constructor(
    private val currencyFormatter: CurrencyFormatter,
    private val bitcoinFormatter: BitcoinFormatter
) {

    fun format(chartType: ChartType, value: Float): String {
        return when (chartType) {
            ChartType.MARKET_PRICE -> currencyFormatter.formatCompact(value.toDouble())
            ChartType.TOTAL_TRANSACTION_FEES -> bitcoinFormatter.format(value, 0)
        }
    }
}