package com.igorvd.bitcoincharts.features.charts.domain.usecase

import com.igorvd.bitcoincharts.features.charts.domain.model.Chart
import com.igorvd.bitcoincharts.features.charts.domain.model.ChartType
import com.igorvd.bitcoincharts.features.charts.domain.repository.BitcoinChartRepository
import javax.inject.Inject

class GetChartUseCase @Inject constructor(
    private val bitcoinChartRepository: BitcoinChartRepository
) {

    suspend fun get(chartType: ChartType): Chart {
        return bitcoinChartRepository.getChart(chartType)
    }
}