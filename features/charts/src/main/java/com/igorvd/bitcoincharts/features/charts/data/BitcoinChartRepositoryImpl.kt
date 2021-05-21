package com.igorvd.bitcoincharts.features.charts.data

import com.igorvd.bitcoincharts.features.charts.data.network.api.BlockchainApi
import com.igorvd.bitcoincharts.features.charts.data.network.mapper.MetricScreenMapper
import com.igorvd.bitcoincharts.features.charts.domain.model.BitcoinMetricScreen
import com.igorvd.bitcoincharts.features.charts.domain.model.ChartType
import com.igorvd.bitcoincharts.features.charts.domain.repository.BitcoinChartRepository
import javax.inject.Inject

class BitcoinChartRepositoryImpl @Inject constructor(
    private val blockchainApi: BlockchainApi,
    private val metricScreenMapper: MetricScreenMapper
) : BitcoinChartRepository {

    override suspend fun getBitcoinMetricScreen(chartType: ChartType): BitcoinMetricScreen {
        val chartResponse = blockchainApi.getChartData(chartType.id)
        return metricScreenMapper.mapFromChart(chartResponse, chartType)
    }
}