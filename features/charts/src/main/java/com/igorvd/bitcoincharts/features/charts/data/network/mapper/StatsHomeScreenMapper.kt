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
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class StatsHomeScreenMapper @Inject constructor(
    @ApplicationContext private val context: Context,
    private val currencyFormatter: CurrencyFormatter,
    private val bitcoinFormatter: BitcoinFormatter,
    private val numberFormatter: NumberFormatter
) {

    fun mapFromStats(stats: StatsResponse): BitcoinStatsHomeScreen {

        return BitcoinStatsHomeScreen(
            title = context.getString(R.string.home_title),
            description = context.getString(R.string.home_description),
            statsCategories = stats.mapCategories()
        )
    }

    private fun StatsResponse.mapCategories(): List<StatsCategory> {
        return listOf(

            // market summary
            StatsCategory(
                title = context.getString(R.string.home_stats_category_market_summary),
                metrics = listOf(
                    BitcoinMetric(
                        label = context.getString(R.string.home_stats_label_market_price),
                        value = currencyFormatter.formatUSD(marketPriceUsd),
                        chartType = ChartType.MARKET_PRICE
                    ),
                    BitcoinMetric(
                        label = context.getString(R.string.home_stats_label_trade_volume),
                        value = currencyFormatter.formatUSD(tradeVolumeUsd)
                    ),
                    BitcoinMetric(
                        label = context.getString(R.string.home_stats_label_trade_volume),
                        value = bitcoinFormatter.format(
                            tradeVolumeBtc,
                            BITCOIN_FRACTION_DIGITS,
                            true
                        )
                    )
                )
            ),

            // block summary
            StatsCategory(
                title = context.getString(R.string.home_stats_category_block_summary),
                metrics = listOf(
                    BitcoinMetric(
                        label = context.getString(R.string.home_stats_label_blocks_mined),
                        value = nBlocksMined.toString()
                    ),
                    BitcoinMetric(
                        label = context.getString(R.string.home_stats_label_time_between_blocks),
                        value = context.getString(
                            R.string.home_stats_value_time_between_blocks,
                            minutesBetweenBlocks.toInt()
                        )
                    ),
                    BitcoinMetric(
                        label = context.getString(R.string.home_stats_label_bitcoins_mined),
                        value = bitcoinFormatter.format(
                            nBtcMined * BITCOIN_FRACTION_FACTOR,
                            BITCOIN_FRACTION_DIGITS,
                            true
                        )
                    )
                )
            ),

            //transaction summary

            StatsCategory(
                title = context.getString(R.string.home_stats_category_transaction_summary),
                metrics = listOf(
                    BitcoinMetric(
                        label = context.getString(R.string.home_stats_label_total_transaction_fees),
                        value = bitcoinFormatter.format(
                            totalFeesBtc * BITCOIN_FRACTION_FACTOR,
                            BITCOIN_FRACTION_DIGITS,
                            true
                        ),
                        chartType = ChartType.TOTAL_TRANSACTION_FEES
                    ),
                    BitcoinMetric(
                        label = context.getString(R.string.home_stats_label_number_of_transactions),
                        value = numberFormatter.format(nTx),
                        chartType = ChartType.NUMBER_OF_TRANSACTIONS
                    ),
                    BitcoinMetric(
                        label = context.getString(R.string.home_stats_label_total_output_volume),
                        value = bitcoinFormatter.format(
                            totalBtcSent * BITCOIN_FRACTION_FACTOR,
                            BITCOIN_FRACTION_DIGITS,
                            true
                        ),
                        chartType = ChartType.OUTPUT_VOLUME
                    ),
                    BitcoinMetric(
                        label = context.getString(R.string.home_stats_label_estimated_transaction_volume),
                        value = bitcoinFormatter.format(
                            estimatedBtcSent * BITCOIN_FRACTION_FACTOR,
                            BITCOIN_FRACTION_DIGITS,
                            true
                        ),
                        chartType = ChartType.ESTIMATED_TRANSACTION_VOLUME_BTC
                    )
                )
            ),

            // mining cost

            StatsCategory(
                title = context.getString(R.string.home_stats_category_mining_cost),
                metrics = listOf(
                    BitcoinMetric(
                        label = context.getString(R.string.home_stats_label_total_miners_revenue),
                        value = currencyFormatter.formatUSD(minersRevenueUsd),
                        chartType = ChartType.MINERS_REVENUE
                    )
                )
            )

        )
    }

    companion object {
        private const val BITCOIN_FRACTION_FACTOR = 0.00000001
        private const val BITCOIN_FRACTION_DIGITS = 8
    }
}


