package com.igorvd.bitcoincharts.features.charts.data.network.model

import com.squareup.moshi.Json

data class ChartResponse(
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "description")
    val description: String,
    @field:Json(name = "values")
    val entries: List<ChartEntryResponse>
)

data class ChartEntryResponse(
    @field:Json(name = "x")
    val x: Long,
    @field:Json(name = "y")
    val y: Double,
)