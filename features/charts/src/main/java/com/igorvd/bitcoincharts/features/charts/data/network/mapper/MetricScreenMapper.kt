package com.igorvd.bitcoincharts.features.charts.data.network.mapper

import com.igorvd.bitcoincharts.core.domain.formatter.CurrencyFormatter
import com.igorvd.bitcoincharts.core.domain.service.datetime.DatePattern
import com.igorvd.bitcoincharts.core.domain.service.datetime.DateTimeService
import com.igorvd.bitcoincharts.features.charts.data.network.model.ChartEntryResponse
import com.igorvd.bitcoincharts.features.charts.data.network.model.ChartResponse
import com.igorvd.bitcoincharts.features.charts.domain.model.BitcoinMetricChart
import com.igorvd.bitcoincharts.features.charts.domain.model.BitcoinMetricScreen
import com.igorvd.bitcoincharts.features.charts.domain.model.ChartType
import com.igorvd.bitcoincharts.features.charts.domain.model.MetricChartEntry
import com.igorvd.bitcoincharts.features.charts.domain.model.MetricChartFormattedEntry
import com.igorvd.bitcoincharts.features.charts.domain.model.XAxisConfig
import com.igorvd.bitcoincharts.features.charts.domain.model.YAxisConfig
import javax.inject.Inject

class MetricScreenMapper @Inject constructor(
    private val dateTimeService: DateTimeService,
    private val currencyFormatter: CurrencyFormatter
) {

    fun mapFromChart(chart: ChartResponse, chartType: ChartType): BitcoinMetricScreen {

        return BitcoinMetricScreen(
            title = chart.name,
            description = chart.description,
            chart = mapMetricChart(chart, chartType)
        )
    }

    private fun mapMetricChart(chart: ChartResponse, chartType: ChartType): BitcoinMetricChart {

        return BitcoinMetricChart(
            type = chartType,
            unit = chart.unit,
            period = chart.period,
            xAxisConfig = mapXAxisConfig(chart.entries),
            yAxisConfig = mapYAxisConfig(chart.entries, chartType),
            entries = mapEntries(chart, chartType)
        )
    }

    private fun mapXAxisConfig(entries: List<ChartEntryResponse>): XAxisConfig {
        return XAxisConfig(
            minValue = entries.firstOrNull()?.x?.toFloat() ?: 0F,
            maxValue = entries.lastOrNull()?.x?.toFloat() ?: 0F
        )
    }

    private fun mapYAxisConfig(entries: List<ChartEntryResponse>, chartType: ChartType): YAxisConfig {

        val minValue = when (chartType) {
            ChartType.MARKET_PRICE -> 0F
        }

        val maxValue = entries.maxByOrNull { it.y }?.y?.toFloat() ?: 0F

        return YAxisConfig(
            minValue = minValue,
            maxValue = maxValue
        )
    }

    private fun mapEntries(chart: ChartResponse, chartType: ChartType): List<MetricChartEntry> {
        return chart.entries.map { it.mapToMetricChartEntry(chartType) }
    }

    private fun ChartEntryResponse.mapToMetricChartEntry(chartType: ChartType): MetricChartEntry {
        return MetricChartEntry(
            x = x,
            y = y,
            formattedEntry = this.formatEntry(chartType)
        )
    }

    private fun ChartEntryResponse.formatEntry(chartType: ChartType): MetricChartFormattedEntry {

        val formattedX = dateTimeService.convertTimestampToDatePattern(x, DatePattern.MM_dd_yy)

        val formattedY = when (chartType) {
            ChartType.MARKET_PRICE -> currencyFormatter.formatUSD(y)
        }

        return MetricChartFormattedEntry(formattedX, formattedY)
    }
}