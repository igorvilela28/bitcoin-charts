package com.igorvd.chuckfacts.domain.exceptions

class MyHttpErrorException @JvmOverloads constructor(
    message: String?,
    throwable: Throwable? = null
) : Exception(message, throwable)