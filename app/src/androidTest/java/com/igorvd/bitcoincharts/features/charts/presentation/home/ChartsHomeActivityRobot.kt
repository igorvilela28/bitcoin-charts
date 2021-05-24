package com.igorvd.bitcoincharts.features.charts.presentation.home

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers
import com.igorvd.bitcoincharts.AssetsLoader
import com.igorvd.bitcoincharts.BaseRobot
import com.igorvd.bitcoincharts.R
import com.igorvd.bitcoincharts.enqueueIOError
import com.igorvd.bitcoincharts.enqueueResponse
import com.igorvd.bitcoincharts.features.charts.domain.model.ChartType
import com.igorvd.bitcoincharts.features.charts.presentation.chart.BitcoinChartActivity
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.Matchers.allOf

class ChartsHomeActivityRobot(private val server: MockWebServer) :
    BaseRobot<ChartsHomeActivityRobot>() {

    private lateinit var scenario: ActivityScenario<ChartsHomeActivity>

    fun launchActivity() = apply {
        scenario = ActivityScenario.launch(ChartsHomeActivity::class.java)
    }

    fun givenStats200Response() = apply {
        val response = AssetsLoader.loadAsset("stats_response_200.json")
        server.enqueueResponse(response, 200)
    }

    fun givenStats500Response() = apply {
        server.enqueueResponse("{}", 500)
    }

    fun givenIOError() = apply {
        server.enqueueIOError("{}")
    }

    fun whenClickOnTryAgain() = apply {
        clickOnView(R.id.btTryAgain)
    }

    fun whenClickOnViewChart(position: Int) = apply {
        clickOnView(R.id.tvViewChart, position)
    }

    fun thenTitleDisplayed(titleRes: Int) = apply {
        checkIsDisplayed(R.id.tvChartsHomeTitle)
        checkViewContainText(R.id.tvChartsHomeTitle, titleRes)
    }

    fun thenDescriptionDisplayed(descriptionRes: Int) = apply {
        checkIsDisplayed(R.id.tvChartsHomeDescription)
        checkViewContainText(R.id.tvChartsHomeDescription, descriptionRes)
    }

    fun thenCategoryTitleAtPositionDisplayed(position: Int, titleRes: Int) = apply {
        nestedScrollTo(R.id.tvChartsCategoryTitle, position)
        checkRecyclerViewAtPositionDisplayed(
            R.id.rvStatsCategories,
            R.id.tvChartsCategoryTitle,
            position
        )
        checkRecyclerViewAtPositionWithText(
            R.id.rvStatsCategories,
            R.id.tvChartsCategoryTitle,
            position,
            getStringById(titleRes)
        )
    }

    fun thenMetricAtPositionDisplayed(
        position: Int,
        labelRes: Int,
        value: String,
        viewChartButtonVisible: Boolean
    ) =
        apply {
            nestedScrollTo(R.id.tvValue, position)
            checkViewContainText(R.id.tvLabel, position, labelRes)
            checkViewContainText(R.id.tvValue, position, value)
            if (viewChartButtonVisible) {
                checkIsDisplayed(R.id.tvViewChart, position)
            } else {
                checkIsHidden(R.id.tvViewChart, position)
            }
        }

    fun thenErrorViewDisplayed() = apply {
        checkIsHidden(R.id.nsvRoot)
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

    fun thenBitcoinChartActivityOpened(chartType: ChartType) = apply {
        intended(
            allOf(
                IntentMatchers.hasComponent(BitcoinChartActivity::class.java.name),
                IntentMatchers.hasExtra(BitcoinChartActivity.EXTRA_CHART_TYPE, chartType)
            )
        )
    }
}