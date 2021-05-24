package com.igorvd.bitcoincharts.features.charts.presentation.chart

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.platform.app.InstrumentationRegistry
import com.igorvd.bitcoincharts.AssetsLoader
import com.igorvd.bitcoincharts.BaseRobot
import com.igorvd.bitcoincharts.R
import com.igorvd.bitcoincharts.enqueueIOError
import com.igorvd.bitcoincharts.enqueueResponse
import com.igorvd.bitcoincharts.features.charts.domain.model.ChartType
import com.igorvd.bitcoincharts.features.charts.presentation.chart.BitcoinChartActivity
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.Matchers.allOf

class BitcoinChartActivityRobot(private val server: MockWebServer) :
    BaseRobot<BitcoinChartActivityRobot>() {

    private lateinit var scenario: ActivityScenario<BitcoinChartActivity>

    fun launchActivity() = apply {
        val intent = BitcoinChartActivity.newIntent(
            ApplicationProvider.getApplicationContext(), ChartType.MARKET_PRICE
        )
        scenario = ActivityScenario.launch(intent)
    }

    fun givenCharts200Response() = apply {
        val response = AssetsLoader.loadAsset("charts_response_200.json")
        server.enqueueResponse(response, 200)
    }

    fun givenCharts500Response() = apply {
        server.enqueueResponse("{}", 500)
    }

    fun givenIOError() = apply {
        server.enqueueIOError("{}")
    }

    fun whenClickOnTryAgain() = apply {
        clickOnView(R.id.btTryAgain)
    }

    fun thenTitleDisplayed(title: String) = apply {
        checkIsDisplayed(R.id.tvToolbarTitle)
        checkViewContainText(R.id.tvToolbarTitle, title)
    }

    fun thenDescriptionDisplayed(description: String) = apply {
        checkIsDisplayed(R.id.tvChartDescription)
        checkViewContainText(R.id.tvChartDescription, description)
    }

    fun thenErrorViewDisplayed() = apply {
        checkIsHidden(R.id.clContent)
        checkIsDisplayed(R.id.errorView)
    }

    fun thenErrorViewHidden() = apply {
        checkIsHidden(R.id.errorView)
    }

    fun thenErrorImgDisplayed(drawableRes: Int) = apply {
        checkIsDisplayed(R.id.ivErrorIcon)
        checkWithDrawable(R.id.ivErrorIcon, drawableRes)
    }

    fun thenErrorMessageDisplayed(textRes: Int) = apply {
        checkIsDisplayed(R.id.tvErrorMessage)
        checkViewContainText(R.id.tvErrorMessage, textRes)
    }

    fun thenTryAgainButtonDisplayed() = apply {
        checkIsDisplayed(R.id.btTryAgain)
    }
}