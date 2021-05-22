package com.igorvd.bitcoincharts.features.charts.presentation.home.model

import com.igorvd.bitcoincharts.core.presentation.view.error.ErrorView
import com.igorvd.bitcoincharts.features.charts.domain.model.BitcoinStatsHomeScreen

sealed class HomeState {
    object Loading : HomeState()
    data class ShowStats(val homeScreen: BitcoinStatsHomeScreen) : HomeState()
    data class ShowError(val errorViewData: ErrorView.ErrorViewData) : HomeState()
}