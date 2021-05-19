package com.igorvd.bitcoincharts.features.charts.data.network.api

import com.igorvd.bitcoincharts.features.charts.data.network.model.ChartResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface BlockchainApi {
    @GET("charts/{chartType}")
    suspend fun getChartData(@Path("chartType") chartType: String): List<ChartResponse>

    companion object {
        const val BASE_URL = "https://api.blockchain.info/"
    }
}