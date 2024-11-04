package com.dmforu.api.controller

import com.dmforu.api.support.error.AppException
import com.dmforu.api.support.error.ErrorMessage
import com.dmforu.api.support.error.ErrorType
import com.dmforu.api.support.response.ApiResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.logging.LogLevel
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.NoHandlerFoundException
import org.springframework.web.servlet.resource.NoResourceFoundException

@RestControllerAdvice
class ApiControllerAdvice {
    private val log: Logger = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(AppException::class)
    fun handleAppException(e: AppException): ResponseEntity<ApiResponse<ErrorMessage>> {
        when (e.errorType.logLevel) {
            LogLevel.ERROR -> log.error("AppException : {}", e.message, e)
            LogLevel.WARN -> log.warn("AppException : {}", e.message, e)
            else -> log.info("AppException : {}", e.message, e)
        }
        return ResponseEntity(ApiResponse.error(e.errorType, e.data), e.errorType.status)
    }

    @ExceptionHandler(NoHandlerFoundException::class)
    fun handleNoResourceFoundException(e: NoHandlerFoundException): ResponseEntity<ApiResponse<ErrorMessage>> {
        return ResponseEntity(ApiResponse.error(ErrorType.NOT_FOUND_ERROR), ErrorType.NOT_FOUND_ERROR.status)
    }

    @ExceptionHandler(NoResourceFoundException::class)
    fun handleNoResourceFoundException(e: NoResourceFoundException): ResponseEntity<ApiResponse<ErrorMessage>> {
        return ResponseEntity(ApiResponse.error(ErrorType.BAD_REQUEST_ERROR), ErrorType.BAD_REQUEST_ERROR.status)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleRequestBodyException(e: MethodArgumentNotValidException): ResponseEntity<ApiResponse<ErrorMessage>> {
        log.error("MethodArgumentNotValidException : {}", e.message, e)
        return ResponseEntity(ApiResponse.error(ErrorType.BAD_REQUEST_ERROR, e.fieldError?.defaultMessage), ErrorType.BAD_REQUEST_ERROR.status)
    }

    @ExceptionHandler(MissingServletRequestParameterException::class)
    fun handleRequestParamException(e: MissingServletRequestParameterException): ResponseEntity<ApiResponse<ErrorMessage>> {
        log.error("RequestParamException : {}", e.message, e)
        return ResponseEntity(ApiResponse.error(ErrorType.BAD_REQUEST_ERROR), ErrorType.BAD_REQUEST_ERROR.status)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ApiResponse<ErrorMessage>> {
        log.error("Exception : {}", e.message, e)
        return ResponseEntity(ApiResponse.error(ErrorType.SERVER_ERROR), ErrorType.SERVER_ERROR.status)
    }
}