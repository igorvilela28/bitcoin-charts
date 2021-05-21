package com.igorvd.bitcoincharts.features.charts.data.network.api

import com.igorvd.bitcoincharts.features.charts.data.network.model.ChartResponse
import com.igorvd.bitcoincharts.features.charts.data.network.model.StatsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface BlockchainApi {

    @GET("stats")
    suspend fun getStats(): StatsResponse

    @GET("charts/{chartType}")
    suspend fun getChartData(
        @Path("chartType") chartType: String
    ): ChartResponse

    companion object {
        const val BASE_URL = "https://api.blockchain.info/"
    }
}