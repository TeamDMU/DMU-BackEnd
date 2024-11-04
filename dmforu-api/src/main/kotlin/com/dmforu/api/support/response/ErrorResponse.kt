package com.dmforu.api.support.response

import com.dmforu.api.support.error.ErrorMessage
import com.dmforu.api.support.error.ErrorType

class ErrorResponse<T> private constructor(
    val error: T,
) : ApiResponse<T>() {
    companion object {

        fun error(error: ErrorType, errorData: Any? = null): ErrorResponse<ErrorMessage> {
            return ErrorResponse(ErrorMessage(error, errorData))
        }

    }

}