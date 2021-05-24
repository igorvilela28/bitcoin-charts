package  com.igorvd.bitcoincharts

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.SocketPolicy
import java.util.concurrent.TimeUnit

private const val BODY_DELAY = 0L

fun MockWebServer.enqueueResponse(json: String, code: Int) {

    enqueue(
        MockResponse()
            .setResponseCode(code)
            .setBodyDelay(BODY_DELAY, TimeUnit.SECONDS)
            .setBody(json)
    )
}

fun MockWebServer.enqueueIOError(json: String) {
    enqueue(
        MockResponse()
            .setResponseCode(200)
            .setBodyDelay(BODY_DELAY, TimeUnit.SECONDS)
            .setSocketPolicy(SocketPolicy.DISCONNECT_AT_START)
            .setBody(json)
    )
}