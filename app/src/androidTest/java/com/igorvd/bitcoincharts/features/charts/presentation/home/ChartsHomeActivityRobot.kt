package com.igorvd.bitcoincharts.features.charts.presentation.home

import androidx.test.core.app.ActivityScenario
import com.igorvd.bitcoincharts.AssetsLoader
import com.igorvd.bitcoincharts.BaseRobot
import com.igorvd.bitcoincharts.R
import com.igorvd.bitcoincharts.enqueueResponse
import com.igorvd.bitcoincharts.enqueueIOError
import okhttp3.mockwebserver.MockWebServer

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

    fun givenStats500Response(amount: Int = 1) = apply {
        for (i in 0..amount) {
            server.enqueueResponse("{}", 500)
        }
    }

    fun givenIOError(amount: Int = 1) = apply {
        for (i in 0..amount) {
            server.enqueueIOError("{}")
        }
    }

    fun thenTitle(titleRes: Int) = apply {
        checkIsDisplayed(R.id.tvChartsHomeTitle)
        checkViewContainText(R.id.tvChartsHomeTitle, titleRes)
    }
}