package com.igorvd.bitcoincharts.features.charts.data.network.mapper

import com.igorvd.bitcoincharts.core.domain.service.datetime.DatePattern
import com.igorvd.bitcoincharts.core.domain.service.datetime.DateTimeService
import com.igorvd.bitcoincharts.features.charts.data.network.model.ChartEntryResponse
import com.igorvd.bitcoincharts.features.charts.data.network.model.ChartResponse
import com.igorvd.bitcoincharts.features.charts.domain.model.BitcoinMetricChart
import com.igorvd.bitcoincharts.features.charts.domain.model.BitcoinMetricScreen
import com.igorvd.bitcoincharts.features.charts.domain.model.ChartType
import com.igorvd.bitcoincharts.features.charts.domain.model.MetricChartEntry
import com.igorvd.bitcoincharts.features.charts.domain.model.MetricChartFormattedEntry
import javax.inject.Inject

class MetricScreenMapper @Inject constructor(
    private val dateTimeService: DateTimeService,
    private val yMapper: MetricScreenChartYMapper
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
            entries = mapEntries(chart, chartType)
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
        val formattedX = dateTimeService.convertTimestampToDatePattern(x, DatePattern.MMM_dd_yyyy)
        val formattedY = yMapper.formatYValue(y, chartType)
        return MetricChartFormattedEntry(formattedX, formattedY)
    }
}