package com.igorvd.bitcoincharts.features.charts.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.igorvd.bitcoincharts.features.charts.data.network.api.BlockchainApi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChartsHomeViewModel @Inject constructor(
    private val api: BlockchainApi
) : ViewModel() {

    suspend fun getChartsHome() {
        val response = api.getChartData("market-price")
        Log.d("Igor", response.toString())
    }

}