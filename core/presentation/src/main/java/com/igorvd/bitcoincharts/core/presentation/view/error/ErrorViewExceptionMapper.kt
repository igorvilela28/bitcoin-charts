package com.igorvd.bitcoincharts.core.presentation.view.error

import com.igorvd.bitcoincharts.core.presentation.R
import com.igorvd.chuckfacts.domain.exceptions.MyHttpErrorException
import com.igorvd.chuckfacts.domain.exceptions.MyIOException

fun Exception.mapToErrorViewData(): ErrorView.ErrorViewData {
    return when (this) {
        is MyIOException -> ErrorView.ErrorViewData(
            drawableRes = R.drawable.ic_error_networking,
            messageRes = R.string.error_network,
            showRetryButton = true
        )
        is MyHttpErrorException -> ErrorView.ErrorViewData(
            drawableRes = R.drawable.ic_error_server,
            messageRes = R.string.error_server,
            showRetryButton = true
        )
        else -> ErrorView.ErrorViewData(
            drawableRes = R.drawable.ic_alert,
            messageRes = R.string.error_generic,
            showRetryButton = false
        )
    }
}