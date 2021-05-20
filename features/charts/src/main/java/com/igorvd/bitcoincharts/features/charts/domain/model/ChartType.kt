package com.igorvd.bitcoincharts.features.charts.domain.model

enum class ChartType(val query: String) {

    MARKET_PRICE("market-price"),
    TRANSACTION_FEES("transaction-fees")
}