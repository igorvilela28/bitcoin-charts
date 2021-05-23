package com.igorvd.bitcoincharts.features.charts.testutil

import com.igorvd.bitcoincharts.features.charts.data.network.model.ChartEntryResponse
import com.igorvd.bitcoincharts.features.charts.data.network.model.ChartResponse
import com.igorvd.bitcoincharts.features.charts.data.network.model.StatsResponse
import com.igorvd.bitcoincharts.features.charts.domain.model.BitcoinMetricChart
import com.igorvd.bitcoincharts.features.charts.domain.model.BitcoinMetricScreen
import com.igorvd.bitcoincharts.features.charts.domain.model.ChartType
import com.igorvd.bitcoincharts.features.charts.domain.model.MetricChartEntry
import com.igorvd.bitcoincharts.features.charts.domain.model.MetricChartFormattedEntry

val entryResponse = ChartEntryResponse(1L, 40000.0)
val chartResponse = ChartResponse(
    name = "Market price",
    description = "Market price across major bitcoin exchanges",
    entries = listOf(entryResponse)
)
val metricChartEntry = MetricChartEntry(
    x = entryResponse.x,
    y = entryResponse.y,
    formattedEntry = MetricChartFormattedEntry(
        formattedX = "May, 22, 2021",
        formattedY = "$ 40,000.00"
    )
)
val bitcoinMetricScreen = BitcoinMetricScreen(
    title = chartResponse.name,
    description = chartResponse.description,
    chart = BitcoinMetricChart(
        type = ChartType.MARKET_PRICE,
        entries = listOf(metricChartEntry)
    )
)

val statsResponse = StatsResponse(
    marketPriceUsd = 40000.00,
    tradeVolumeUsd = 40000.00,
    tradeVolumeBtc = 75450.76,
    nBlocksMined = 2,
    minutesBetweenBlocks = 4.0,
    nBtcMined = 1250000000,
    totalFeesBtc = 207054247,
    nTx = 5469,
    totalBtcSent = 7848876193320,
    estimatedBtcSent = 1400761641078,
    minersRevenueUsd = 588581.58,
)