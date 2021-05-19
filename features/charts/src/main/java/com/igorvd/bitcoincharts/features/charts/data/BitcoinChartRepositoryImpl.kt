package com.igorvd.bitcoincharts.features.charts.data

import com.igorvd.bitcoincharts.features.charts.data.network.api.BlockchainApi
import com.igorvd.bitcoincharts.features.charts.domain.model.Chart
import com.igorvd.bitcoincharts.features.charts.domain.model.ChartType
import com.igorvd.bitcoincharts.features.charts.domain.repository.BitcoinChartRepository
import javax.inject.Inject

class BitcoinChartRepositoryImpl @Inject constructor(
    private val blockchainApi: BlockchainApi
) : BitcoinChartRepository {

    override suspend fun getChart(chartType: ChartType): Chart {
        return blockchainApi.getChartData(chartType.query).toChart()
    }
}