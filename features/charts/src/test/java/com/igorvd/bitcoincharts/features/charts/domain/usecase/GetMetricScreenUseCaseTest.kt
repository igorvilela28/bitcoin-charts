package com.igorvd.bitcoincharts.features.charts.domain.usecase

import com.igorvd.bitcoincharts.features.charts.domain.model.ChartType
import com.igorvd.bitcoincharts.features.charts.domain.repository.BitcoinChartRepository
import com.igorvd.bitcoincharts.features.charts.testutil.bitcoinMetricScreen
import com.igorvd.bitcoincharts.features.charts.testutil.homeScreen
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetMetricScreenUseCaseTest {

    @MockK
    private lateinit var bitcoinChartRepository: BitcoinChartRepository

    private lateinit var getMetricScreenUseCase: GetMetricScreenUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        getMetricScreenUseCase = GetMetricScreenUseCase(bitcoinChartRepository)
    }

    @Test
    fun `should get bitcoin metric screen`() = runBlocking {

        coEvery { bitcoinChartRepository.getBitcoinMetricScreen(ChartType.MARKET_PRICE) } returns bitcoinMetricScreen

        val result = getMetricScreenUseCase.get(ChartType.MARKET_PRICE)

        assertEquals(bitcoinMetricScreen, result)
        coVerify(exactly = 1) { bitcoinChartRepository.getBitcoinMetricScreen(ChartType.MARKET_PRICE) }
    }
}