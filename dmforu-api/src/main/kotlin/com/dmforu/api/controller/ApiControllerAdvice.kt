package com.dmforu.api.controller

import com.dmforu.api.support.error.AppException
import com.dmforu.api.support.error.ErrorType
import com.dmforu.api.support.response.ApiResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.logging.LogLevel
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.net.BindException

@RestControllerAdvice
class ApiControllerAdvice {
    private val log: Logger = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(AppException::class)
    fun handleAppException(e: AppException): ResponseEntity<ApiResponse<Any>> {
        when (e.errorType.logLevel) {
            LogLevel.ERROR -> log.error("AppException : {}", e.message, e)
            LogLevel.WARN -> log.warn("AppException : {}", e.message, e)
            else -> log.info("AppException : {}", e.message, e)
        }
        return ResponseEntity(ApiResponse.error(e.errorType, e.data), e.errorType.status)
    }

    @ExceptionHandler(BindException::class)
    fun handleRequestException(e: BindException): ResponseEntity<ApiResponse<Any>> {
        log.error("BindException : {}", e.message, e)
        return ResponseEntity(ApiResponse.error(ErrorType.BAD_REQUEST_ERROR), ErrorType.BAD_REQUEST_ERROR.status)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ApiResponse<Any>> {
        log.error("Exception : {}", e.message, e)
        return ResponseEntity(ApiResponse.error(ErrorType.SERVER_ERROR), ErrorType.SERVER_ERROR.status)
    }
}