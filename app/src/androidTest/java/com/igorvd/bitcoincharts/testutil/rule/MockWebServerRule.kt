package com.igorvd.bitcoincharts.testutil.rule

import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class MockWebServerRule : TestWatcher() {

    lateinit var mockWebServer: MockWebServer

    override fun starting(description: Description?) {
        mockWebServer = MockWebServer()
        mockWebServer.start(8080)
    }

    override fun finished(description: Description?) {
        mockWebServer.shutdown()
    }
}