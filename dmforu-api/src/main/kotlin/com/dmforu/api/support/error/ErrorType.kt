package com.dmforu.api.support.error

import org.springframework.boot.logging.LogLevel
import org.springframework.http.HttpStatus

enum class ErrorType(val status: HttpStatus, val code: ErrorCode, val message: String, val logLevel: LogLevel) {
    BAD_REQUEST_ERROR(HttpStatus.BAD_REQUEST, ErrorCode.E400, "잘못된 요청을 하였습니다.", LogLevel.WARN),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.E500, "An unexpected error has occurred.", LogLevel.ERROR),
}