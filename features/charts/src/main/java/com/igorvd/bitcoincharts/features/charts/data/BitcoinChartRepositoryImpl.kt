package com.igorvd.bitcoincharts.features.charts.data

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

    override suspend fun getBitcoinStatsHomeScreen(): BitcoinStatsHomeScreen {
        val statsResponse = blockchainApi.getStats()
        return statsHomeScreenMapper.mapFromStats(statsResponse)
    }

    override suspend fun getBitcoinMetricScreen(chartType: ChartType): BitcoinMetricScreen {
        val chartResponse = blockchainApi.getChartData(chartType.id)
        return metricScreenMapper.mapFromChart(chartResponse, chartType)
    }
}