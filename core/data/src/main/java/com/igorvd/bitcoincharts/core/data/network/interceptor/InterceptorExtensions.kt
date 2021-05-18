package com.igorvd.bitcoincharts.core.data.network.interceptor

import okhttp3.logging.HttpLoggingInterceptor

/**
 * Extension function that returns a simple logging with the level of
 *  [HttpLoggingInterceptor.Level.BODY]
 */
fun getSimpleLogging(): HttpLoggingInterceptor {

    return HttpLoggingInterceptor().apply {
        // logging: use NONE | HEADERS | BASIC | BODY to see request data
        level = HttpLoggingInterceptor.Level.BODY
    }
}