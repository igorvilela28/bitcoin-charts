package com.igorvd.bitcoincharts.features.charts.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.igorvd.bitcoincharts.features.charts.data.network.api.BlockchainApi
import com.igorvd.bitcoincharts.features.charts.domain.model.ChartType
import com.igorvd.bitcoincharts.features.charts.domain.usecase.GetChartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChartsHomeViewModel @Inject constructor(
    private val getChartUseCase: GetChartUseCase
) : ViewModel() {

    suspend fun getChartsHome() {
        val response = getChartUseCase.get(ChartType.MARKET_PRICE)
        Log.d("Igor", response.toString())
    }
}