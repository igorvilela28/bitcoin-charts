package com.igorvd.bitcoincharts.features.charts.data.network.model

import com.igorvd.bitcoincharts.features.charts.domain.model.Chart
import com.igorvd.bitcoincharts.features.charts.domain.model.ChartEntry
import com.squareup.moshi.Json

data class ChartResponse(
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "unit")
    val unit: String,
    @field:Json(name = "period")
    val period: String,
    @field:Json(name = "description")
    val description: String,
    @field:Json(name = "values")
    val entries: List<ChartEntryResponse>
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
    val x: Long,
    @field:Json(name = "y")
    val y: Double,
) {
    fun toChartEntry() = ChartEntry(
        x = x,
        y = y
    )
}