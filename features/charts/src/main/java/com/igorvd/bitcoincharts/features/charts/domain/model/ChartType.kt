package com.igorvd.bitcoincharts.features.charts.domain.model

enum class ChartType(val id: String) {
    MARKET_PRICE("market-price"),
    TOTAL_TRANSACTION_FEES("transaction-fees"),
    NUMBER_OF_TRANSACTIONS("n-transactions"),
    OUTPUT_VOLUME("output-volume"),
    ESTIMATED_TRANSACTION_VOLUME_BTC("estimated-transaction-volume"),
    MINERS_REVENUE("miners-revenue")
}