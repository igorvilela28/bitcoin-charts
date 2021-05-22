package com.igorvd.chuckfacts.domain.exceptions

class MyHttpErrorException : Exception {
    constructor(message: String, code: Int) : super(message)
    constructor(message: String, code: Int, cause: Throwable) : super(message, cause)
}