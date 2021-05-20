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
    val xAxisConfig: XAxisConfig,
    val yAxisConfig: YAxisConfig,
    val entries: List<MetricChartEntry>
)

data class XAxisConfig(
    val minValue: Float,
    val maxValue: Float
)

data class YAxisConfig(
    val minValue: Float,
    val maxValue: Float
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