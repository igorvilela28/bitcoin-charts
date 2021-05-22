package com.igorvd.bitcoincharts.features.charts.data

import com.igorvd.bitcoincharts.core.data.network.request.doRequest
import com.igorvd.bitcoincharts.features.charts.data.network.api.BlockchainApi
import com.igorvd.bitcoincharts.features.charts.data.network.mapper.MetricScreenMapper
import com.igorvd.bitcoincharts.features.charts.data.network.mapper.StatsHomeScreenMapper
import com.igorvd.bitcoincharts.features.charts.domain.model.BitcoinMetricScreen
import com.igorvd.bitcoincharts.features.charts.domain.model.BitcoinStatsHomeScreen
import com.igorvd.bitcoincharts.features.charts.domain.model.ChartType
import com.igorvd.bitcoincharts.features.charts.domain.repository.BitcoinChartRepository
import javax.inject.Inject

class BitcoinChartRepositoryImpl @Inject constructor(
    private val blockchainApi: BlockchainApi,
    private val metricScreenMapper: MetricScreenMapper,
    private val statsHomeScreenMapper: StatsHomeScreenMapper
) : BitcoinChartRepository {

    override suspend fun getBitcoinStatsHomeScreen() = doRequest {
        val statsResponse = blockchainApi.getStats()
        statsHomeScreenMapper.mapFromStats(statsResponse)
    }

    override suspend fun getBitcoinMetricScreen(chartType: ChartType) = doRequest {
        val chartResponse = blockchainApi.getChartData(chartType.id)
        metricScreenMapper.mapFromChart(chartResponse, chartType)
    }
}