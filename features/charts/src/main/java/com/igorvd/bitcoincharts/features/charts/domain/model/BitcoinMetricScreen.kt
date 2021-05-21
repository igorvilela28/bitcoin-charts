package com.igorvd.bitcoincharts.features.charts.domain.model

data class BitcoinMetricScreen(
    val title: String,
    val description: String,
    val chart: BitcoinMetricChart
)

data class BitcoinMetricChart(
    val type: ChartType,
    val unit: String,
    val period: String,
    val entries: List<MetricChartEntry>
)

data class MetricChartEntry(
    val x: Long,
    val y: Double,
    val formattedEntry: MetricChartFormattedEntry
)

data class MetricChartFormattedEntry(
    val formattedX: String,
    val formattedY: String
)