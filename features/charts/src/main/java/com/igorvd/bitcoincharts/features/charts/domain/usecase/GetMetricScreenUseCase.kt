package com.igorvd.bitcoincharts.features.charts.domain.usecase

import com.igorvd.bitcoincharts.features.charts.domain.model.BitcoinMetricScreen
import com.igorvd.bitcoincharts.features.charts.domain.model.ChartType
import com.igorvd.bitcoincharts.features.charts.domain.repository.BitcoinChartRepository
import javax.inject.Inject

class GetMetricScreenUseCase @Inject constructor(
    private val bitcoinChartRepository: BitcoinChartRepository
) {

    suspend fun get(chartType: ChartType): BitcoinMetricScreen {
        return bitcoinChartRepository.getBitcoinMetricScreen(chartType)
    }
}