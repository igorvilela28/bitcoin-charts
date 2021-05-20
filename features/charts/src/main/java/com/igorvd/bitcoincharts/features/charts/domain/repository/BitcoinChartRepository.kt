package com.igorvd.bitcoincharts.features.charts.domain.repository

import com.igorvd.bitcoincharts.features.charts.domain.model.BitcoinMetricScreen
import com.igorvd.bitcoincharts.features.charts.domain.model.Chart
import com.igorvd.bitcoincharts.features.charts.domain.model.ChartType

interface BitcoinChartRepository {
    suspend fun getChart(chartType: ChartType): Chart
    suspend fun getBitcoinMetricScreen(chartType: ChartType): BitcoinMetricScreen
}