package com.igorvd.bitcoincharts.features.charts.testutil

import com.squareup.moshi.Moshi

inline fun <reified T> String.parseJson(): T? {
    val jsonAdapter = Moshi.Builder().build().run {
        adapter(T::class.java)
    }
    return jsonAdapter.fromJson(this)
}