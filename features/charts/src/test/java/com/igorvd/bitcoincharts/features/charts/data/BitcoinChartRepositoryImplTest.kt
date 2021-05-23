package com.igorvd.bitcoincharts.features.charts.data

import com.igorvd.bitcoincharts.core.data.network.ApiClientBuilder
import com.igorvd.bitcoincharts.features.charts.data.network.api.BlockchainApi
import com.igorvd.bitcoincharts.features.charts.data.network.mapper.MetricScreenMapper
import com.igorvd.bitcoincharts.features.charts.data.network.mapper.StatsHomeScreenMapper
import com.igorvd.bitcoincharts.features.charts.data.network.model.ChartResponse
import com.igorvd.bitcoincharts.features.charts.data.network.model.StatsResponse
import com.igorvd.bitcoincharts.features.charts.domain.model.ChartType
import com.igorvd.bitcoincharts.features.charts.testutil.AssetLoader
import com.igorvd.bitcoincharts.features.charts.testutil.bitcoinMetricScreen
import com.igorvd.bitcoincharts.features.charts.testutil.enqueueResponse
import com.igorvd.bitcoincharts.features.charts.testutil.homeScreen
import com.igorvd.bitcoincharts.features.charts.testutil.parseJson
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class BitcoinChartRepositoryImplTest {

    private lateinit var server: MockWebServer
    private lateinit var blockchainApi: BlockchainApi

    @MockK
    private lateinit var metricScreenMapper: MetricScreenMapper

    @MockK
    private lateinit var statsHomeScreenMapper: StatsHomeScreenMapper

    private lateinit var repository: BitcoinChartRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        server = MockWebServer()
        server.start()
        val url = server.url("/").toString()
        blockchainApi = ApiClientBuilder.createService(BlockchainApi::class.java, url)
        repository =
            BitcoinChartRepositoryImpl(blockchainApi, metricScreenMapper, statsHomeScreenMapper)
    }

    @Test
    fun `should get BitcoinStatsHomeScreen when blockchain API returns 200 from get stats`() =
        runBlocking {

            val statsResponseJson = AssetLoader.loadJsonFromResources(
                javaClass.classLoader!!,
                "stats_response_200.json"
            )
            server.enqueueResponse(statsResponseJson, 200)
            val statsResponse = statsResponseJson.parseJson<StatsResponse>()
            every { statsHomeScreenMapper.mapFromStats(statsResponse!!) } returns homeScreen

            val response = repository.getBitcoinStatsHomeScreen()

            assertEquals(homeScreen, response)

        }

    @Test
    fun `should get BitcoinMetricScreen when blockchain API returns 200 from get chartData`() =
        runBlocking {

            val chartResponseJson = AssetLoader.loadJsonFromResources(
                javaClass.classLoader!!,
                "charts_response_200.json"
            )
            server.enqueueResponse(chartResponseJson, 200)
            val chartResponse = chartResponseJson.parseJson<ChartResponse>()
            every {
                metricScreenMapper.mapFromChart(
                    chartResponse!!,
                    ChartType.MARKET_PRICE
                )
            } returns bitcoinMetricScreen

            val response = repository.getBitcoinMetricScreen(ChartType.MARKET_PRICE)

            assertEquals(bitcoinMetricScreen, response)

        }
}