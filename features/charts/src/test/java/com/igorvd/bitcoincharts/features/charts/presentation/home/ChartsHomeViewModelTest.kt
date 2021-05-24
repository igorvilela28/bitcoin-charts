package com.igorvd.bitcoincharts.features.charts.presentation.home

import com.igorvd.bitcoincharts.core.presentation.view.error.ErrorView
import com.igorvd.bitcoincharts.core.presentation.view.error.ErrorViewExceptionMapper
import com.igorvd.bitcoincharts.features.charts.domain.usecase.GetHomeStatsScreenUseCase
import com.igorvd.bitcoincharts.features.charts.presentation.home.model.HomeState
import com.igorvd.bitcoincharts.features.charts.testutil.homeScreen
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

class ChartsHomeViewModelTest {

    @MockK
    private lateinit var getHomeStatsScreenUseCase: GetHomeStatsScreenUseCase
    @MockK
    private lateinit var errorViewExceptionMapper: ErrorViewExceptionMapper

    private lateinit var viewModel: ChartsHomeViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = ChartsHomeViewModel(getHomeStatsScreenUseCase, errorViewExceptionMapper)
    }

    @Test
    fun `should update to state ShowStats when retrieve stats screen successfully`() = runBlocking {

        coEvery { getHomeStatsScreenUseCase.get() } returns homeScreen

        viewModel.getHomeScreen()

        assertEquals(HomeState.ShowStats(homeScreen), viewModel.homeStateFlow.value)
        coVerify(exactly = 1) {
            getHomeStatsScreenUseCase.get()
        }
        verify { errorViewExceptionMapper wasNot Called }
    }

    @Test
    fun `should update to state ShowError when exception is thrown`() = runBlocking {

        val exception = Exception()
        coEvery { getHomeStatsScreenUseCase.get() } throws exception
        val errorViewData = ErrorView.ErrorViewData(0, 0, true)
        every { errorViewExceptionMapper.mapToErrorViewData(exception) } returns errorViewData

        viewModel.getHomeScreen()

        assertEquals(HomeState.ShowError(errorViewData), viewModel.homeStateFlow.value)
        coVerifyOrder {
            getHomeStatsScreenUseCase.get()
            errorViewExceptionMapper.mapToErrorViewData(exception)
        }
    }
}