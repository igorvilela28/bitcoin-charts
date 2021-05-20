package com.igorvd.bitcoincharts.features.charts.presentation.chart.view.linechart.formatter

import com.igorvd.bitcoincharts.core.domain.formatter.CurrencyFormatter
import com.igorvd.bitcoincharts.features.charts.domain.model.ChartType
import javax.inject.Inject

class YAxisFormatterFactory @Inject constructor(
    private val currencyFormatter: CurrencyFormatter
) {

    fun format(chartType: ChartType, value: Float): String {
        return when (chartType) {
            ChartType.MARKET_PRICE -> currencyFormatter.formatCompact(value.toDouble())
        }
    }
}