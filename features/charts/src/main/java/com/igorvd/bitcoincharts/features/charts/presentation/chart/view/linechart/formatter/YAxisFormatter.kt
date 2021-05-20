package com.igorvd.bitcoincharts.features.charts.presentation.chart.view.linechart.formatter

import com.github.mikephil.charting.formatter.ValueFormatter
import com.igorvd.bitcoincharts.features.charts.domain.model.ChartType

class YAxisFormatter(
    private val chartType: ChartType,
    private val yAxisFormatterFactory: YAxisFormatterFactory
) : ValueFormatter() {

    override fun getFormattedValue(value: Float): String {
        return yAxisFormatterFactory.format(chartType, value)
    }
}