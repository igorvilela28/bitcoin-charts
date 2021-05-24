package com.igorvd.bitcoincharts.features.charts.presentation.home

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.igorvd.bitcoincharts.R
import com.igorvd.bitcoincharts.features.charts.data.di.ChartsDataModule
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
class ChartsHomeActivityTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val mockWebServerRule = MockWebServerRule()

    @get:Rule
    val intentsRule = IntentsRule()

    private lateinit var robot: ChartsHomeActivityRobot

    @Before
    fun setup() {
        robot = ChartsHomeActivityRobot(mockWebServerRule.mockWebServer)
    }

    @Test
    fun shouldShowHomeScreen_whenStatsApiResponse200() {
        robot
            .givenStats200Response()
            .launchActivity()
            .thenTitle(R.string.home_title)
    }

}