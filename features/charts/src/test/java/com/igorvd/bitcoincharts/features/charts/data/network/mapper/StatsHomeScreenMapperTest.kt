package com.igorvd.bitcoincharts.features.charts.data.network.mapper

import android.content.Context
import com.igorvd.bitcoincharts.core.domain.formatter.BitcoinFormatter
import com.igorvd.bitcoincharts.core.domain.formatter.CurrencyFormatter
import com.igorvd.bitcoincharts.core.domain.formatter.NumberFormatter
import com.igorvd.bitcoincharts.features.charts.R
import com.igorvd.bitcoincharts.features.charts.data.network.model.StatsResponse
import com.igorvd.bitcoincharts.features.charts.domain.model.BitcoinMetric
import com.igorvd.bitcoincharts.features.charts.domain.model.BitcoinStatsHomeScreen
import com.igorvd.bitcoincharts.features.charts.domain.model.ChartType
import com.igorvd.bitcoincharts.features.charts.domain.model.StatsCategory
import com.igorvd.bitcoincharts.features.charts.testutil.statsResponse
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class StatsHomeScreenMapperTest {

    @MockK
    private lateinit var context: Context

    @MockK
    private lateinit var currencyFormatter: CurrencyFormatter

    @MockK
    private lateinit var bitcoinFormatter: BitcoinFormatter

    @MockK
    private lateinit var numberFormatter: NumberFormatter

    private lateinit var mapper: StatsHomeScreenMapper

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        val stringIdSlot = slot<Int>()
        val stringIntArgumentSlot = slot<Int>()
        every { context.getString(capture(stringIdSlot)) } answers {
            stringIdSlot.captured.toString()
        }
        every { context.getString(capture(stringIdSlot), capture(stringIntArgumentSlot)) } answers {
            "${stringIdSlot.captured} ${stringIntArgumentSlot.captured}"
        }

        mapper =
            StatsHomeScreenMapper(context, currencyFormatter, bitcoinFormatter, numberFormatter)
    }

    @Test
    fun `should map ChartResponse to BitcoinMetricScreen`() {

        val expectedBitcoinStatsHomeScreen = buildExpectedBitcoinStatsHomeScreen()

        val result = mapper.mapFromStats(statsResponse)

        assertEquals(expectedBitcoinStatsHomeScreen, result)

    }

    private fun buildExpectedBitcoinStatsHomeScreen(): BitcoinStatsHomeScreen {
        return BitcoinStatsHomeScreen(
            title = R.string.home_title.toString(),
            description = R.string.home_description.toString(),
            statsCategories = buildExpectedCategories()
        )
    }

    private fun buildExpectedCategories(): List<StatsCategory> {
        return listOf(
            buildMarketSummaryCategory(),
            buildBlockSummaryCategory(),
            buildTransactionSummaryCategory(),
            buildMiningCostCategory()
        )
    }

    private fun buildMarketSummaryCategory(): StatsCategory {

        val expectedMarketPrice = "$40,000.00"
        every { currencyFormatter.formatUSD(statsResponse.marketPriceUsd) } returns expectedMarketPrice

        val expectedTradeVolumeUsd = "$40,000.00"
        every { currencyFormatter.formatUSD(statsResponse.tradeVolumeUsd) } returns expectedTradeVolumeUsd

        val expectedTradeVolumeBtc = "75,450.76000000"
        every {
            bitcoinFormatter.format(
                statsResponse.tradeVolumeBtc,
                StatsHomeScreenMapper.BITCOIN_FRACTION_DIGITS,
                true
            )
        } returns expectedTradeVolumeBtc

        return StatsCategory(
            title = R.string.home_stats_category_market_summary.toString(),
            metrics = listOf(
                BitcoinMetric(
                    label = R.string.home_stats_label_market_price.toString(),
                    value = expectedMarketPrice,
                    chartType = ChartType.MARKET_PRICE
                ),
                BitcoinMetric(
                    label = R.string.home_stats_label_trade_volume.toString(),
                    value = expectedTradeVolumeUsd
                ),
                BitcoinMetric(
                    label = R.string.home_stats_label_trade_volume.toString(),
                    value = expectedTradeVolumeBtc
                )
            )
        )
    }

    private fun buildBlockSummaryCategory(): StatsCategory {

        val expectedBitcoinsMined = "12.500000000 BTC"
        every {
            bitcoinFormatter.format(
                statsResponse.nBtcMined * StatsHomeScreenMapper.BITCOIN_FRACTION_FACTOR,
                StatsHomeScreenMapper.BITCOIN_FRACTION_DIGITS,
                true
            )
        } returns expectedBitcoinsMined

        return StatsCategory(
            title = R.string.home_stats_category_block_summary.toString(),
            metrics = listOf(
                BitcoinMetric(
                    label = R.string.home_stats_label_blocks_mined.toString(),
                    value = statsResponse.nBlocksMined.toString()
                ),
                BitcoinMetric(
                    label = R.string.home_stats_label_time_between_blocks.toString(),
                    value = "${R.string.home_stats_value_time_between_blocks} ${statsResponse.minutesBetweenBlocks.toInt()}",
                ),
                BitcoinMetric(
                    label = R.string.home_stats_label_bitcoins_mined.toString(),
                    value = expectedBitcoinsMined
                )
            )
        )
    }

    private fun buildTransactionSummaryCategory(): StatsCategory {

        val expectedTotalFees = "2.07054247 BTC"
        every {
            bitcoinFormatter.format(
                statsResponse.totalFeesBtc * StatsHomeScreenMapper.BITCOIN_FRACTION_FACTOR,
                StatsHomeScreenMapper.BITCOIN_FRACTION_DIGITS,
                true
            )
        } returns expectedTotalFees

        val expectedNtx = "5,469"
        every { numberFormatter.format(statsResponse.nTx) } returns expectedNtx

        val expectedTotalBtcSend = "78,488.76193320 BTC"
        every {
            bitcoinFormatter.format(
                statsResponse.totalBtcSent * StatsHomeScreenMapper.BITCOIN_FRACTION_FACTOR,
                StatsHomeScreenMapper.BITCOIN_FRACTION_DIGITS,
                true
            )
        } returns expectedTotalBtcSend

        val expectedEstimatedBtcSent = "14,007.61641078 BTC"
        every {
            bitcoinFormatter.format(
                statsResponse.estimatedBtcSent * StatsHomeScreenMapper.BITCOIN_FRACTION_FACTOR,
                StatsHomeScreenMapper.BITCOIN_FRACTION_DIGITS,
                true
            )
        } returns expectedEstimatedBtcSent

        return StatsCategory(
            title = R.string.home_stats_category_transaction_summary.toString(),
            metrics = listOf(
                BitcoinMetric(
                    label = R.string.home_stats_label_total_transaction_fees.toString(),
                    value = expectedTotalFees,
                    chartType = ChartType.TOTAL_TRANSACTION_FEES
                ),
                BitcoinMetric(
                    label = R.string.home_stats_label_number_of_transactions.toString(),
                    value = expectedNtx,
                    chartType = ChartType.NUMBER_OF_TRANSACTIONS
                ),
                BitcoinMetric(
                    label = R.string.home_stats_label_total_output_volume.toString(),
                    value = expectedTotalBtcSend,
                    chartType = ChartType.OUTPUT_VOLUME
                ),
                BitcoinMetric(
                    label = R.string.home_stats_label_estimated_transaction_volume.toString(),
                    value = expectedEstimatedBtcSent,
                    chartType = ChartType.ESTIMATED_TRANSACTION_VOLUME_BTC
                )
            )
        )
    }

    private fun buildMiningCostCategory(): StatsCategory {

        val expectedMinersRevenueUsd = "$588,581.58"
        every { currencyFormatter.formatUSD(statsResponse.minersRevenueUsd) } returns expectedMinersRevenueUsd

        return StatsCategory(
            title = R.string.home_stats_category_mining_cost.toString(),
            metrics = listOf(
                BitcoinMetric(
                    label = R.string.home_stats_label_total_miners_revenue.toString(),
                    value = expectedMinersRevenueUsd,
                    chartType = ChartType.MINERS_REVENUE
                )
            )
        )
    }
}