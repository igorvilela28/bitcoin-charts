package com.igorvd.bitcoincharts.features.charts.presentation.chart

import com.igorvd.bitcoincharts.core.presentation.view.error.ErrorView
import com.igorvd.bitcoincharts.core.presentation.view.error.ErrorViewExceptionMapper
import com.igorvd.bitcoincharts.features.charts.domain.model.ChartType
import com.igorvd.bitcoincharts.features.charts.domain.usecase.GetMetricScreenUseCase
import com.igorvd.bitcoincharts.features.charts.presentation.chart.model.ChartScreenState
import com.igorvd.bitcoincharts.features.charts.testutil.bitcoinMetricScreen
import io.mockk.Called
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifyOrder
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class BitcoinChartViewModelTest {

    @MockK
    private lateinit var getMetricScreenUseCase: GetMetricScreenUseCase
    @MockK
    private lateinit var errorViewExceptionMapper: ErrorViewExceptionMapper

    private lateinit var viewModel: BitcoinChartViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = BitcoinChartViewModel(getMetricScreenUseCase, errorViewExceptionMapper)
    }

    @Test
    fun `should update to state ShowStats when retrieve stats screen successfully`() = runBlocking {

        coEvery { getMetricScreenUseCase.get(ChartType.MARKET_PRICE) } returns bitcoinMetricScreen

        viewModel.getChartData(ChartType.MARKET_PRICE)

        assertEquals(ChartScreenState.ShowMetricScreen(bitcoinMetricScreen), viewModel.chartScreenStateFlow.value)
        coVerify(exactly = 1) {
            getMetricScreenUseCase.get(ChartType.MARKET_PRICE)
        }
        verify { errorViewExceptionMapper wasNot Called }
    }

    @Test
    fun `should update to state ShowError when exception is thrown`() = runBlocking {

        val exception = Exception()
        coEvery { getMetricScreenUseCase.get(ChartType.MARKET_PRICE) } throws exception
        val errorViewData = ErrorView.ErrorViewData(0, 0, true)
        every { errorViewExceptionMapper.mapToErrorViewData(exception) } returns errorViewData

        viewModel.getChartData(ChartType.MARKET_PRICE)

        assertEquals(ChartScreenState.ShowError(errorViewData), viewModel.chartScreenStateFlow.value)
        coVerifyOrder {
            getMetricScreenUseCase.get(ChartType.MARKET_PRICE)
            errorViewExceptionMapper.mapToErrorViewData(exception)
        }
    }
}