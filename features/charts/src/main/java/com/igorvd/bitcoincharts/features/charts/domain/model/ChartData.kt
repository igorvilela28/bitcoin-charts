package com.igorvd.bitcoincharts.features.charts.domain.model

data class Chart(
    val name: String,
    val unit: String,
    val period: String,
    val description: String,
    val entries: List<ChartEntry>
)

data class ChartEntry(
    val x: Long,
    val y: Double
)