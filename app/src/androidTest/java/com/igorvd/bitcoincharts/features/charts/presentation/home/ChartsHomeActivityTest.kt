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
            .thenTitleDisplayed(R.string.home_title)
            .thenDescriptionDisplayed(R.string.home_description)
            .thenCategoryTitleAtPositionDisplayed(0, R.string.home_stats_category_market_summary)
            .thenMetricAtPositionDisplayed(
                0,
                R.string.home_stats_label_market_price,
                "$40,395.31",
                true
            )
            .thenMetricAtPositionDisplayed(
                1,
                R.string.home_stats_label_trade_volume,
                "$400,000,000.00",
                false
            )
            .thenMetricAtPositionDisplayed(
                2,
                R.string.home_stats_label_trade_volume,
                "75,450.76000000 BTC",
                false
            )
            .thenCategoryTitleAtPositionDisplayed(1, R.string.home_stats_category_block_summary)
            .thenMetricAtPositionDisplayed(
                3,
                R.string.home_stats_label_blocks_mined,
                "2",
                false
            )
            .thenMetricAtPositionDisplayed(
                4,
                R.string.home_stats_label_time_between_blocks,
                "4 minutes",
                false
            )
            .thenMetricAtPositionDisplayed(
                5,
                R.string.home_stats_label_bitcoins_mined,
                "12.50000000 BTC",
                false
            )
            .thenCategoryTitleAtPositionDisplayed(
                2,
                R.string.home_stats_category_transaction_summary
            )
            .thenMetricAtPositionDisplayed(
                6,
                R.string.home_stats_label_total_transaction_fees,
                "2.07054247 BTC",
                true
            )
            .thenMetricAtPositionDisplayed(
                7,
                R.string.home_stats_label_number_of_transactions,
                "5,469",
                true
            )
            .thenMetricAtPositionDisplayed(
                8,
                R.string.home_stats_label_total_output_volume,
                "78,488.76193320 BTC",
                true
            )
            .thenMetricAtPositionDisplayed(
                9,
                R.string.home_stats_label_estimated_transaction_volume,
                "14,007.61641078 BTC",
                true
            )
            .thenCategoryTitleAtPositionDisplayed(
                3,
                R.string.home_stats_category_mining_cost
            )
            .thenMetricAtPositionDisplayed(
                10,
                R.string.home_stats_label_total_miners_revenue,
                "$588,581.58",
                true
            )
    }
}