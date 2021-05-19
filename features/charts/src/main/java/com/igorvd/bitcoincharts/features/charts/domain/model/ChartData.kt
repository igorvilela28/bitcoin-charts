package com.igorvd.bitcoincharts.features.charts.domain.model

data class Chart(
    private val name: String,
    private val unit: String,
    private val period: String,
    private val description: String,
    private val entries: List<ChartEntry>
)

data class ChartEntry(
    private val x: Long,
    private val y: Double
)