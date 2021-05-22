package com.igorvd.bitcoincharts.core.data.network.request

import com.igorvd.chuckfacts.domain.exceptions.MyHttpErrorException
import com.igorvd.chuckfacts.domain.exceptions.MyIOException
import retrofit2.HttpException
import java.io.IOException

inline fun <T> doRequest(block: () -> T): T {
    try {
        return block()
    } catch (e: IOException) {
        throw MyIOException(e.message, e)
    } catch (e: HttpException) {
        throw MyHttpErrorException(e.message(), e)
    }
}