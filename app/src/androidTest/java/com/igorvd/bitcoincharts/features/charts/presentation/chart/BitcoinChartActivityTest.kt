package com.igorvd.bitcoincharts.features.charts.presentation.chart

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.igorvd.bitcoincharts.R
import com.igorvd.bitcoincharts.features.charts.data.di.ChartsDataModule
import com.igorvd.bitcoincharts.features.charts.domain.model.ChartType
import com.igorvd.bitcoincharts.testutil.rule.IntentsRule
import com.igorvd.bitcoincharts.testutil.rule.MockWebServerRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@UninstallModules(ChartsDataModule::class)
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class BitcoinChartActivityTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val mockWebServerRule = MockWebServerRule()

    @get:Rule
    val intentsRule = IntentsRule()

    private lateinit var robot: BitcoinChartActivityRobot

    @Before
    fun setup() {
        robot = BitcoinChartActivityRobot(mockWebServerRule.mockWebServer)
    }

    @Test
    fun shouldShowChartScreen_whenChartsApiResponse200() {
        robot
            .givenCharts200Response()
            .launchActivity()
            .thenErrorViewHidden()
            .thenTitleDisplayed("Market Price (USD)")
            .thenDescriptionDisplayed("Average USD market price across major bitcoin exchanges.")
    }

    @Test
    fun shouldShowErrorLayoutWithServerError_whenApiResponse500() {
        robot
            .givenCharts500Response()
            .launchActivity()
            .thenErrorViewDisplayed()
            .thenErrorImgDisplayed(R.drawable.ic_error_server)
            .thenErrorMessageDisplayed(R.string.error_server)
            .thenTryAgainButtonDisplayed()
    }

    @Test
    fun shouldShowErrorLayoutWithNetworkError_whenIOException() {
        robot
            .givenIOError()
            .launchActivity()
            .thenErrorViewDisplayed()
            .thenErrorImgDisplayed(R.drawable.ic_error_networking)
            .thenErrorMessageDisplayed(R.string.error_network)
            .thenTryAgainButtonDisplayed()
    }

    @Test
    fun shouldShowChartsScreen_whenClickOnTryAgain() {
        robot
            .givenIOError()
            .givenCharts200Response()
            .launchActivity()
            .whenClickOnTryAgain()
            .thenTitleDisplayed("Market Price (USD)")
    }
}