package com.igorvd.bitcoincharts.features.charts.domain.model

data class BitcoinStatsHomeScreen(
    val title: String,
    val description: String,
    val statsCategories: List<StatsCategory>
)

data class StatsCategory(
    val title: String,
    val metrics: List<BitcoinMetric>
)

data class BitcoinMetric(
    val label: String,
    val value: String,
    val chartType: ChartType? = null
)