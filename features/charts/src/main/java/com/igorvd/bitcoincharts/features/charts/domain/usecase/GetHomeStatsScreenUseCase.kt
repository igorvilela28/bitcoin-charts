package com.igorvd.bitcoincharts.features.charts.domain.usecase

import com.igorvd.bitcoincharts.features.charts.domain.model.BitcoinStatsHomeScreen
import com.igorvd.bitcoincharts.features.charts.domain.repository.BitcoinChartRepository
import javax.inject.Inject

class GetHomeStatsScreenUseCase @Inject constructor(
    private val bitcoinChartRepository: BitcoinChartRepository
) {

    suspend fun get(): BitcoinStatsHomeScreen {
        return bitcoinChartRepository.getBitcoinStatsHomeScreen()
    }
}