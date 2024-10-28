package com.dmforu.api.support.error

class AppException(
    val errorType: ErrorType,
    val data: Any? = null,
) : RuntimeException(errorType.message)