package com.igorvd.bitcoincharts.features.charts.testutil

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.SocketPolicy
import java.util.concurrent.TimeUnit

private const val BODY_DELAY = 0L

fun MockWebServer.enqueueIOError(body: String) {

    enqueue(
        MockResponse()
            .setResponseCode(200)
            .setBodyDelay(BODY_DELAY, TimeUnit.SECONDS)
            .setSocketPolicy(SocketPolicy.NO_RESPONSE)
            .setBody(body)
    )
}

fun MockWebServer.enqueueResponse(body: String, code: Int) {
    enqueue(
        MockResponse()
            .setResponseCode(code)
            .setBodyDelay(BODY_DELAY, TimeUnit.SECONDS)
            .setBody(body)
    )
}