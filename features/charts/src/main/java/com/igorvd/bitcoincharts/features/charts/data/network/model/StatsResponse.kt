package com.igorvd.bitcoincharts.features.charts.data.network.model

import com.squareup.moshi.Json

data class StatsResponse(
    @field:Json(name = "market_price_usd")
    val marketPriceUsd: Double,
    @field:Json(name = "trade_volume_usd")
    val tradeVolumeUsd: Double,
    @field:Json(name = "trade_volume_btc")
    val tradeVolumeBtc: Double,
    @field:Json(name = "n_btc_mined")
    val nBtcMined: Int,
    @field:Json(name = "minutes_between_blocks")
    val minutesBetweenBlocks: Double,
    @field:Json(name = "n_blocks_mined")
    val nBlocksMined: Int,
    @field:Json(name = "total_fees_btc")
    val totalFeesBtc: Int,
    @field:Json(name = "n_tx")
    val nTx: Int,
    @field:Json(name = "total_btc_sent")
    val totalBtcSent: Long,
    @field:Json(name = "estimated_btc_sent")
    val estimatedBtcSent: Long,
    @field:Json(name = "miners_revenue_usd")
    val minersRevenueUsd: Double
)