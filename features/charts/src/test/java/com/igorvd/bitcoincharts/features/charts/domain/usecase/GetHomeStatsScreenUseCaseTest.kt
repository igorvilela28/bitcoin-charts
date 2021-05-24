package com.igorvd.bitcoincharts.features.charts.domain.usecase

import com.igorvd.bitcoincharts.features.charts.domain.repository.BitcoinChartRepository
import com.igorvd.bitcoincharts.features.charts.testutil.homeScreen
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetHomeStatsScreenUseCaseTest {

    @MockK
    private lateinit var bitcoinChartRepository: BitcoinChartRepository

    private lateinit var getHomeStatsScreenUseCase: GetHomeStatsScreenUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        getHomeStatsScreenUseCase = GetHomeStatsScreenUseCase(bitcoinChartRepository)
    }

    @Test
    fun `should get bitcoin stats screen`() = runBlocking {

        coEvery { bitcoinChartRepository.getBitcoinStatsHomeScreen() } returns homeScreen

        val result = getHomeStatsScreenUseCase.get()

        assertEquals(homeScreen, result)
        coVerify(exactly = 1) { bitcoinChartRepository.getBitcoinStatsHomeScreen() }
    }
}