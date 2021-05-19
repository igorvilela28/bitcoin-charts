package com.igorvd.bitcoincharts.features.charts.data.network.model

import com.igorvd.bitcoincharts.features.charts.domain.model.Chart
import com.igorvd.bitcoincharts.features.charts.domain.model.ChartEntry
import com.squareup.moshi.Json

data class ChartResponse(
    @field:Json(name = "name")
    private val name: String,
    @field:Json(name = "unit")
    private val unit: String,
    @field:Json(name = "period")
    private val period: String,
    @field:Json(name = "description")
    private val description: String,
    @field:Json(name = "values")
    private val entries: List<ChartEntryResponse>
) {
    fun toChart() = Chart(
        name = name,
        unit = unit,
        period = period,
        description = description,
        entries = entries.map { it.toChartEntry() }
    )
}

data class ChartEntryResponse(
    @field:Json(name = "x")
    private val x: Long,
    @field:Json(name = "y")
    private val y: Double,
) {
    fun toChartEntry() = ChartEntry(
        x = x,
        y = y
    )
}